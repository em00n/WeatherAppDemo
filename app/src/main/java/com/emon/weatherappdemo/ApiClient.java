package com.emon.weatherappdemo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BaseUrl="https://api.openweathermap.org/";
    public static final String appliedId="73bb1dd44b0cc93ec1b695164df7e092";
    public static String lat = "28.21";
    public static String lon = "79.54";
    private static Retrofit retrofit=null;

    public static Retrofit getClient(){
        if (retrofit == null) {
            retrofit=new Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
