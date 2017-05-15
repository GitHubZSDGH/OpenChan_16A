package test.jiyun.com.openchan_16a.activity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import test.jiyun.com.openchan_16a.R;
import test.jiyun.com.openchan_16a.base.BaseActivity;
import test.jiyun.com.openchan_16a.config.FragmentBuilder;
import test.jiyun.com.openchan_16a.homefragment.zonghe.ZongHeFragment;

public class MainActivity extends BaseActivity {

    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.title_search)
    ImageView titleSearch;
    @Bind(R.id.TitleBar)
    RelativeLayout TitleBar;
    @Bind(R.id.zhuye_content)
    FrameLayout zhuyeContent;
    @Bind(R.id.Main_Zonghe)
    RadioButton MainZonghe;
    @Bind(R.id.Main_dongtan)
    RadioButton MainDongtan;
    @Bind(R.id.main_login)
    ImageView mainLogin;
    @Bind(R.id.Main_faxian)
    RadioButton MainFaxian;
    @Bind(R.id.Main_wode)
    RadioButton MainWode;
    @Bind(R.id.Main_RadioGroup)
    RadioGroup MainRadioGroup;



    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
//        FragmentBuilder.getInstanca().start(ZongHeFragment.class, R.id.zhuye_content)
//                .builder();
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.add(R.id.zhuye_content,new ZongHeFragment());
//        transaction.commit();
        FragmentBuilder.getInstanca().start(ZongHeFragment.class,R.id.zhuye_content).builder();

    }

    @Override
    protected void initLisenter() {

    }

    @Override
    protected void initLoadData() {

    }

    @OnClick({R.id.title_search, R.id.Main_Zonghe, R.id.Main_dongtan, R.id.main_login, R.id.Main_faxian, R.id.Main_wode, R.id.Main_RadioGroup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_search:
                break;
            case R.id.Main_Zonghe:
                FragmentBuilder.getInstanca().start(ZongHeFragment.class, R.id.zhuye_content)
                        .builder();
                break;
            case R.id.Main_dongtan:
                break;
            case R.id.main_login:
                break;
            case R.id.Main_faxian:
                break;
            case R.id.Main_wode:
                break;
            case R.id.Main_RadioGroup:
                break;
        }
    }
}
