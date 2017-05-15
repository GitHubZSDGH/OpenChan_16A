package test.jiyun.com.openchan_16a.modle.https;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import test.jiyun.com.openchan_16a.App;
import test.jiyun.com.openchan_16a.modle.callback.MyCallBack;

/**
 * Created by lenovo on 2017/5/9.
 */

public class Retorfitimpl implements IHttp {
    private IRetrofit iRetrofit;
    private final SharedPreferences sharedPreferences;

    private Retorfitimpl() {
        sharedPreferences = App.baseActivity.getSharedPreferences("data", Context.MODE_PRIVATE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.baidu.com/")
                .build();
        iRetrofit = retrofit.create(IRetrofit.class);
    }

    private static Retorfitimpl retorfitimpl;

    public static synchronized Retorfitimpl getInstance() {
        if (retorfitimpl == null) {
            retorfitimpl = new Retorfitimpl();
        }
        return retorfitimpl;
    }

    public static void RetrofitimpleClean() {
        retorfitimpl = null;
    }

    @Override
    public void Get(String url, Map<String, String> map, final MyCallBack callBack) {
        Call<ResponseBody> call = iRetrofit.Get(sharedPreferences.getString("cookie", ""), url, map);
        initRetorfit(callBack, call);
    }

    private void initRetorfit(final MyCallBack callBack, Call<ResponseBody> call) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        callBack.succseeful(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        callBack.errorMessage(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBack.errorMessage(t.getMessage() + "");
            }
        });
    }

    @Override
    public void Post(String url, Map<String, String> map, MyCallBack callBack) {
Call<ResponseBody> call = iRetrofit.Post(url,map);
        initRetorfit(callBack,call);
    }
}
