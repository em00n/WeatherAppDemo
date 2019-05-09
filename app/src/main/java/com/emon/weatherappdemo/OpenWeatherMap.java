package com.emon.weatherappdemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface OpenWeatherMap {


    @GET("data/2.5/weather?")
    Call<Application> getCurrentWeather(
            @Query("lat") String lat,
            @Query("lon") String lon,
            @Query("APPID") String appid
    );

    @GET
    Call<Application> getCurrentWeather(@Url String url);
}
