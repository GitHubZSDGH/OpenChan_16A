package test.jiyun.com.openchan_16a.modle.https;

import android.support.annotation.Nullable;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by lenovo on 2017/5/9.
 */

public interface IRetrofit {
    @GET
    Call<ResponseBody> Get(@Nullable @Header("Cookie") String cookie, @Url String url, @QueryMap Map<String, String> map);

    @FormUrlEncoded
    @POST
    Call<ResponseBody> Post(@Url String url, @FieldMap Map<String, String> map);

}
