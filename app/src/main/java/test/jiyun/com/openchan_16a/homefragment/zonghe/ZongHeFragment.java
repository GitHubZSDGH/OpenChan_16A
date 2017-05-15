package test.jiyun.com.openchan_16a.homefragment.zonghe;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import test.jiyun.com.openchan_16a.R;
import test.jiyun.com.openchan_16a.base.BaseFragment;

/**
 * Created by lenovo on 2017/5/9.
 */

public class ZongHeFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.zonghe_title)
    TabLayout zongheTitle;
    @Bind(R.id.zonghe_add)
    ImageView zongheAdd;
    @Bind(R.id.zonghe_viewpager)
    ViewPager zongheViewpager;
    private List<String> listName = new ArrayList<>();
    private List<Fragment> listBaseFragment = new ArrayList<>();


    @Override
    protected int getLayout() {
        return R.layout.zonghe_fragment;
    }

    @Override
    protected void initView(View view) {

    }



    @Override
    protected void initLisenter() {
        zongheAdd.setOnClickListener(this);

    }

    @Override
    protected void initLoadData() {
        listName.add("开源资讯");
        listName.add("推荐博客");
        listName.add("技术问答");
        listName.add("每日一博");
        listName.add("云计算");
        listName.add("软件工程");
        listName.add("最新翻译");
        listBaseFragment.add(new ZongHeHomeFragment());
        listBaseFragment.add(new ZongHeHomeFragment());
        listBaseFragment.add(new ZongHeHomeFragment());
        listBaseFragment.add(new ZongHeHomeFragment());
        listBaseFragment.add(new ZongHeHomeFragment());
        listBaseFragment.add(new ZongHeHomeFragment());
        listBaseFragment.add(new ZongHeHomeFragment());
        zongheTitle.setTabMode(TabLayout.MODE_SCROLLABLE);
        zongheViewpager.setAdapter(new ZongHeAdapter(getActivity().getSupportFragmentManager()));
        zongheTitle.setupWithViewPager(zongheViewpager);
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

    @Override
    public void onClick(View v) {

    }

    class ZongHeAdapter extends FragmentStatePagerAdapter{

    public ZongHeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return listBaseFragment.get(position);
    }

    @Override
    public int getCount() {
        return listBaseFragment.isEmpty() ? 0 : listBaseFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listName.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}




}
