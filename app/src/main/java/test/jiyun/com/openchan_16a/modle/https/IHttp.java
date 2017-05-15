package test.jiyun.com.openchan_16a.modle.https;

import java.util.Map;

import test.jiyun.com.openchan_16a.modle.callback.MyCallBack;

/**
 * Created by lenovo on 2017/5/9.
 */

public interface IHttp {
    void Get(String url, Map<String,String> map, MyCallBack callBack);
    void Post(String url, Map<String,String> map, MyCallBack callBack);
}
