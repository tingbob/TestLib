package org.tingbob.testlib;

import android.content.Context;

/**
 * Created by tingbob on 2016/12/14.
 */
public class RequestParams {

    public static Context mContext;

    private static RequestParams mInstance = new RequestParams();

    public static RequestParams getInstance() {
        return mInstance;
    }

    private RequestParams() {
    }

    public void init(Context context) {
        mContext = context;
    }
}
