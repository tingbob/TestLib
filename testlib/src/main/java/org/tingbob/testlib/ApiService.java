package org.tingbob.testlib;

import org.tingbob.testlib.model.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by tingbob on 2016/12/14.
 */

public interface ApiService {
    @GET("adat/sk/{cityId}.html")
    Call<Weather<Weather.WeatherInfo>> getWeather(@Path("cityId") String cityId);
}
