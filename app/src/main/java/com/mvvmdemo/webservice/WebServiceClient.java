package com.mvvmdemo.webservice;

import com.mvvmdemo.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class WebServiceClient {

    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";


    private static Retrofit retrofit = null;
    private static Retrofit.Builder builder;
    private static OkHttpClient.Builder httpClient;

    private WebServiceClient() {
        super();
    }

    public static Retrofit getClient() {

        if (builder == null) {
            builder = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

        }

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            if (BuildConfig.DEBUG)
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(loggingInterceptor);
            builder.client(httpClient.build());


        retrofit = builder.build();

        return retrofit;
    }



}