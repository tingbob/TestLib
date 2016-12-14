package org.tingbob.testlib.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.tingbob.testlib.ServiceGenerator;
import org.tingbob.testlib.model.Weather;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ServiceGenerator.getInstance().init(this);
        findViewById(R.id.tv_hello_world).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServiceGenerator.getInstance().getWeather(new Callback<Weather<Weather.WeatherInfo>>() {
                    @Override
                    public void onResponse(Call<Weather<Weather.WeatherInfo>> call, Response<Weather<Weather.WeatherInfo>> response) {
                        Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<Weather<Weather.WeatherInfo>> call, Throwable throwable) {

                    }
                }, "101010100");
            }
        });
    }
}
