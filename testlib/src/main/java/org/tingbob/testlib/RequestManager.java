package org.tingbob.testlib;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by tingbob on 2016/12/14.
 */
public class RequestManager {
    private static RequestManager ourInstance = new RequestManager();

    public static RequestManager getInstance() {
        return ourInstance;
    }

    private RequestManager() {
    }

    public void test(Context context) {
        Toast.makeText(context, "this is a test", Toast.LENGTH_LONG).show();
    }
}
