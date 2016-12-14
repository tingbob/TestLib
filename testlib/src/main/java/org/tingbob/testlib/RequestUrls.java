package org.tingbob.testlib;

import android.content.Context;

/**
 * Created by tingbob on 2016/12/14.
 */
public class RequestUrls {

    public static Context mContext;

    public final static String HOST_URL = "http://www.weather.com.cn/";

    private static RequestUrls mInstance = new RequestUrls();

    public static RequestUrls getInstance() {
        return mInstance;
    }

    private RequestUrls() {
    }

    public void init(Context context) {
        mContext = context;
    }
}
