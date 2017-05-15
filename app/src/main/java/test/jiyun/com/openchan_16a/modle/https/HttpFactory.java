package test.jiyun.com.openchan_16a.modle.https;

/**
 * Created by lenovo on 2017/5/9.
 */

public class HttpFactory {
    //    public static final int OKHTTP = 1;
//    public static final int RETROFIT = 2;


    public static IHttp getFactory() {
        IHttp iuSer = Retorfitimpl.getInstance();
//        switch (Inter) {
//            case OKHTTP:
//                iuSer = OkHttpUtils.getOkHttp();
//                return iuSer;
//            case RETROFIT:
//                iuSer = Retorfitimpl.getInstance();
//                return iuSer;
//        }
        return iuSer;
    }
}
