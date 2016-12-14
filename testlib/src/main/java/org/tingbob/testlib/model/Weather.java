package org.tingbob.testlib.model;

import java.io.Serializable;

/**
 * Created by tingbob on 15/7/14.
 */
public class Weather<T> implements Serializable {

    public static class WeatherInfo implements Serializable {
        private String city;
        private String temp;
        private String time;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    private T weatherinfo;

    public T getWeatherinfo() {
        return weatherinfo;
    }

    public void setWeatherinfo(T weatherinfo) {
        this.weatherinfo = weatherinfo;
    }
}
