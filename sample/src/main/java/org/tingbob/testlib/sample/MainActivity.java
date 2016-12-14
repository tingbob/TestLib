package org.tingbob.testlib.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.tingbob.testlib.RequestManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_hello_world).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestManager.getInstance().test(MainActivity.this);
            }
        });
    }
}
