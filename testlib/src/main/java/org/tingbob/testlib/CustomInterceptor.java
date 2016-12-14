package org.tingbob.testlib;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tingbob on 2016/11/17.
 */

public class CustomInterceptor implements Interceptor {

    private Context mContext;

    public void setContext(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
//        HttpUrl url = originalRequest.url().newBuilder()
//                .addQueryParameter("lang", SharedPreferences.getLanguage(HarkHarkApplication.getInstance().getApplicationContext()))
//                .addQueryParameter("region", SharedPreferences.getCity(HarkHarkApplication.getInstance().getApplicationContext()))
//                .addQueryParameter("version", Utils.getVersion(HarkHarkApplication.getInstance().getApplicationContext()))
//                .build();

        originalRequest = originalRequest.newBuilder()
//                .url(url)
                .headers(Headers.of(getHeaders()))
                .build();

        return chain.proceed(originalRequest);
    }

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new LinkedHashMap<>();
        String uuid = getDeviceUUID();
        headers.put("deviceuuid", uuid);

        return headers;
    }

    private String getDeviceUUID() {
        String uniqueId = "";
        try {
            TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
            String tmDevice, tmSerial, androidId;

            tmDevice = "" + tm.getDeviceId();
            tmSerial = "" + tm.getSimSerialNumber();

            androidId = "" + android.provider.Settings.Secure.getString(mContext.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

            UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());

            uniqueId = deviceUuid.toString();
        } catch (Exception e) {
            Log.d("getDeviceUUID", e.getMessage());
        }

        return uniqueId;
    }
}
