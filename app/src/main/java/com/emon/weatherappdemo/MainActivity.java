package com.emon.weatherappdemo;

import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Comment;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView textView1,textView2,textView3,textView4;
    ImageView imageView1,imageView2;
    OpenWeatherMap openWeatherMap;
    int t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = findViewById(R.id.t1);
        textView2 = findViewById(R.id.t2);
        textView3 = findViewById(R.id.t3);
        textView4 = findViewById(R.id.t4);
        imageView1 = findViewById(R.id.i1);
        imageView2 = findViewById(R.id.i2);
        imageView1.setImageResource(R.drawable.ic_wb_sunny_black_24dp);
        imageView2.setImageResource(R.drawable.celcius);


        openWeatherMap = ApiClient.getClient().create(OpenWeatherMap.class);


        getCurrentWeather();



    }



    @Override
    protected void onRestart() {
        getCurrentWeather();
        super.onRestart();
    }

    @Override
    protected void onResume() {
        getCurrentWeather();
        super.onResume();
    }

    private void getCurrentWeather() {
        Call<Application> call = openWeatherMap.getCurrentWeather(ApiClient.lat,ApiClient.lon,ApiClient.appliedId);
        //Call<Application> call = openWeatherMap.getCurrentWeather("data/2.5/weather?q=dhaka&appid=73bb1dd44b0cc93ec1b695164df7e092");

        call.enqueue(new Callback<Application>() {
            @Override
            public void onResponse(Call<Application> call, Response<Application> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                }
                Application application = response.body();
               String temp=String.valueOf(application.main.temp);
                double conver = Double.parseDouble(temp);
                double value = conver - 273.15;
                int tempr=(int) value;
                t=tempr;

                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                String currentTime = sdf.format(new Date());
                SimpleDateFormat sdf1 = new SimpleDateFormat("EE", Locale.ENGLISH);
                String currentDay = sdf1.format(new Date());

                textView1.setText(application.name);
                textView2.setText("Bangladesh");
                textView3.setText(currentDay+"  "+currentTime);
                textView4.setText(String.valueOf(tempr));


                }
                double conver(String v){
                    double conver = Double.parseDouble(v);
                    double value = conver - 273.15;
                    return value;
                }




            @Override
            public void onFailure(Call<Application> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}
