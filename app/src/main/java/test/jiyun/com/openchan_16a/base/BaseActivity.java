package test.jiyun.com.openchan_16a.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import test.jiyun.com.openchan_16a.App;

/**
 * Created by lenovo on 2017/5/9.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.baseActivity = this;
        setContentView(getLayout());
        ButterKnife.bind(this);
        initView();
        initLisenter();
        initLoadData();

    }
    //找布局
   protected abstract int getLayout();
    //布局ID
    protected abstract void initView();
    //设置各个组件的监听
    protected abstract void initLisenter();
    //加载数据
    protected abstract void initLoadData();

    @Override
    protected void onResume() {
        super.onResume();
        App.baseActivity=this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
//back键回退
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
