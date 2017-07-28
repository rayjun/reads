package cn.rayjun.readhub.api.impl;

import cn.rayjun.readhub.util.Constant;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ray on 26/07/2017.
 */

public abstract class BaseApi <T>{
    protected Retrofit retrofit;

    public BaseApi() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();


        retrofit = new Retrofit
                .Builder()
                .baseUrl(Constant.NEWS_API_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    public T getService() {
        return retrofit.create(getServiceClass());
    }


    protected abstract Class<T> getServiceClass();
}
