package test.jiyun.com.openchan_16a.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by lenovo on 2017/5/9.
 */

public abstract class BaseFragment extends Fragment {
    private boolean boo = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayout(),container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        initView(view);
        initLisenter();
    }

    //找布局
    protected abstract int getLayout();
    //布局ID
    protected abstract void initView(View view);
    //设置各个组件的监听
    protected abstract void initLisenter();
    //加载数据
    protected abstract void initLoadData();
    public void setParmars(Bundle bundle) {


    }
    @Override
    public void onResume() {
        super.onResume();
        if(boo){
            initLoadData();
            updateActionBar();
        }

    }

    private void updateActionBar() {
    }

    @Override
    public void onPause() {
        super.onPause();
        boo = false;
    }

    @Override
    public void onStop() {
        super.onStop();

    }
    protected  void getparems(Bundle bundle){}

    protected void onShow() {
        updateActionBar();
    }

    protected void onHidden() {
    }
}
