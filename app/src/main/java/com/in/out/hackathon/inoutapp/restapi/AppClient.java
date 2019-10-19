package com.in.out.hackathon.inoutapp.restapi;

import android.content.Context;

import com.in.out.hackathon.inoutapp.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AppClient {

    private static AppClient mInstance;
    private Context context;

    private AppClient() {
    }

    public static synchronized AppClient getInstance() {
        if (mInstance == null) mInstance = new AppClient();
        return mInstance;
    }

    public <S> S createService(Class<S> serviceClass) {

        OkHttpClient.Builder httpClient = getOKHttpClient();
        OkHttpClient client = httpClient.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(serviceClass);
    }



    public <S> S createServiceWithCache(Class<S> serviceClass) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(serviceClass);
    }



    private OkHttpClient.Builder getOKHttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(httpLoggingInterceptor);
        }

        httpClient.connectTimeout(15, TimeUnit.SECONDS);
        httpClient.writeTimeout(15, TimeUnit.SECONDS);
        httpClient.readTimeout(15, TimeUnit.SECONDS);

        return httpClient;
    }


}