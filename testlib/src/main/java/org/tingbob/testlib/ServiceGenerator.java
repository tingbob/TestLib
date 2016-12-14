package org.tingbob.testlib;

import android.content.Context;

import org.tingbob.testlib.model.Weather;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tingbob on 2016/12/14.
 */
public class ServiceGenerator {

    public static Context mContext;

    private final int SOCKET_TIMEOUT = 30;

    private Retrofit.Builder mRetrofitBuilder;

    private OkHttpClient.Builder mOkHttpClientBuilder;

    private CustomInterceptor mCustomInterceptor;

    private ApiService mApiService;

    private static ServiceGenerator mInstance = new ServiceGenerator();

    public static ServiceGenerator getInstance() {
        return mInstance;
    }

    private ServiceGenerator() {
    }

    public void init(Context context) {
        mContext = context.getApplicationContext();

        mCustomInterceptor = new CustomInterceptor();
        mCustomInterceptor.setContext(context);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        mOkHttpClientBuilder = new OkHttpClient.Builder()
                .addInterceptor(mCustomInterceptor)
                .addInterceptor(logging)
                .connectTimeout(SOCKET_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(SOCKET_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);

        mRetrofitBuilder = new Retrofit.Builder()
                .client(mOkHttpClientBuilder.build())
                .baseUrl(RequestUrls.HOST_URL)
                .addConverterFactory(GsonConverterFactory.create());


        mApiService = createService(ApiService.class);

        RequestUrls.getInstance().init(context);
        RequestParams.getInstance().init(context);
    }

    private  <T> T createService(Class<T> serviceClass) {
        Retrofit retrofit = mRetrofitBuilder.build();
        return retrofit.create(serviceClass);
    }

    public void getWeather(Callback<Weather<Weather.WeatherInfo>> callback, String cityId) {
        Call<Weather<Weather.WeatherInfo>> call = mApiService.getWeather(cityId);
        call.enqueue(callback);
    }
}
