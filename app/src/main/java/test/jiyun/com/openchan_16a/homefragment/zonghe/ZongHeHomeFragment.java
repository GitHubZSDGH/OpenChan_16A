package test.jiyun.com.openchan_16a.homefragment.zonghe;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.PullToRefreshRecyclerView;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import test.jiyun.com.openchan_16a.App;
import test.jiyun.com.openchan_16a.R;
import test.jiyun.com.openchan_16a.base.BaseFragment;
import test.jiyun.com.openchan_16a.modle.adapter.ZiXunAdapter;
import test.jiyun.com.openchan_16a.modle.bean.ZiXunBean;
import test.jiyun.com.openchan_16a.modle.callback.MyCallBack;
import test.jiyun.com.openchan_16a.modle.https.HttpFactory;
import test.jiyun.com.openchan_16a.utils.Utils;

/**
 * Created by lenovo on 2017/5/9.
 */

public class ZongHeHomeFragment extends BaseFragment {
    ViewPager ziXunLunBo;
    @Bind(R.id.zixun_pullview)
    PullToRefreshRecyclerView zixunPullview;
    private List<ZiXunBean.NewsBean> listZiXunbean = new ArrayList<>();
    private List<View> list = new ArrayList<>();
    private View view1;
    private int currentItem = 10000000;
    private final int COOD_START = 1;
    private final int COOD_STOP = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case COOD_START:
                    ziXunLunBo.setCurrentItem(currentItem++);
                    handler.sendEmptyMessageDelayed(COOD_START, 2000);
                    break;
                case COOD_STOP:
                    handler.removeMessages(COOD_STOP);
                    break;
            }
        }
    };

    @Override
    protected int getLayout() {
        return R.layout.zixun_lunbo;
    }

    @Override
    protected void initView(View view) {
        View view1 = LayoutInflater.from(App.baseActivity).inflate(R.layout.hear_lunbo, null);
        ziXunLunBo = (ViewPager) view1.findViewById(R.id.zonghe_title_banner);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext());
        getRetro();
        zixunPullview.setLayoutManager(manager);
        getLunbo();
        zixunPullview.addHeaderView(view1);
    }
     //走吧走吧
    private void getRetro() {
        Map<String, String> map = new HashMap<>();
        map.put("catalog", "1");
        map.put("pageIndex", "1");
        map.put("pageSize", "20");
        HttpFactory.getFactory().Get(Utils.NEWS_InFORMETION, map, new MyCallBack() {
            @Override
            public void succseeful(String body) {
                //开始解析
                XStream xStream = new XStream();
                xStream.alias("oschina", ZiXunBean.class);
                xStream.alias("news", ZiXunBean.NewsBean.class);
                ZiXunBean informetion = (ZiXunBean) xStream.fromXML(body);
                Log.i("zonghehome", informetion.getNewslist().toString());
                //添加到集合 并且显示出来
                listZiXunbean.addAll(informetion.getNewslist());
                zixunPullview.setAdapter(new ZiXunAdapter(
                        getActivity().getBaseContext(), R.layout.item_style, listZiXunbean));
            }

            @Override
            public void errorMessage(String errorMessage) {
            }
        });
    }

    @Override
    protected void initLisenter() {

    }

    @Override
    protected void initLoadData() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void showLunBo() {
        ziXunLunBo.setAdapter(new LunBoAdapter(list));
        ziXunLunBo.setCurrentItem(currentItem++);
        ziXunLunBo.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("第一个方法", "s");
            }

            @Override
            public void onPageSelected(int position) {
                Log.e("第二个方法", "ss");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e("第三个方法", "sss");
            }
        });
    }

    private void getLunbo() {
        View view = LayoutInflater.from(App.baseActivity).inflate(R.layout.item_test1, null);
        View view2 = LayoutInflater.from(App.baseActivity).inflate(R.layout.item_test2, null);
        View view3 = LayoutInflater.from(App.baseActivity).inflate(R.layout.item_test3, null);
        list.add(view);
        list.add(view2);
        list.add(view3);
        showLunBo();
    }

    class LunBoAdapter extends PagerAdapter {
        private List<View> list;

        public LunBoAdapter(List<View> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            //获取int最大值
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if (container != null) {

                container.removeView(list.get(position % list.size()));
            }
            container.addView(list.get(position % list.size()));

            return list.get(position % list.size());
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            handler.sendEmptyMessageDelayed(COOD_START, 2000);
        }
        if (aBoolean) {
            aBoolean = false;
        } else {
            handler.sendEmptyMessage(COOD_STOP);
        }
    }

    private boolean aBoolean = true;

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.sendEmptyMessage(COOD_STOP);
    }
}
