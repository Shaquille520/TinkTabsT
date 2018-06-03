package com.higo.tinklabstest.http;

import android.content.Context;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sharkliu on 2018/4/3.
 */

public class HttpHelper {
    private static int DEFAULT_TIMEOUT=10;
    private HashMap<String,Object>serviceMap;
    private Context context;
    private String baseUrl="";

    Gson gson = new GsonBuilder()
            .addSerializationExclusionStrategy(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    return f.getAnnotation(MyExclus.class) != null;
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    return false;
                }
            })
            .create();
    public HttpHelper(Context context){
        this.context=context;
        serviceMap=new HashMap<>();
    }
    public HttpHelper(){
        serviceMap=new HashMap<>();
    }
    public <S> S getService(Class<S> serviceClass){
        if(serviceMap.containsKey(serviceClass.getName())){
            return (S)serviceMap.get(serviceClass.getName());
        }else{
            Object obj=createService(serviceClass);
            serviceMap.put(serviceClass.getName(),obj);
            return (S)obj;
        }
    }

    private <S> Object createService(Class<S> serviceClass) {

        OkHttpClient.Builder httpClient=new OkHttpClient.Builder();

        httpClient.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClient.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClient.readTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS);

        if(context!=null){
            File httpCacheDirectory = new File(context.getCacheDir(), "OkHttpCache");
            httpClient.cache(new Cache(httpCacheDirectory, 10 * 1024 * 1024));
        }


     //   httpClient.addNetworkInterceptor(new LogInterceptor());
      //  httpClient.addInterceptor(new CacheControlInterceptor());
        return createService(serviceClass, httpClient.build());
    }

    private <S> Object createService(Class<S> serviceClass, OkHttpClient client) {
        try{
            Field field1=serviceClass.getField("baseUrl");
            baseUrl=(String)field1.get(serviceClass);
        }catch (NoSuchFieldException e){
            baseUrl="";
        }catch (IllegalAccessException e){
            baseUrl="";
        }
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl( baseUrl.equals("") ? Constant.BASE_URL:baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        return retrofit.create(serviceClass);
    }

    public void setBaseUrl(String baseUrl) {

        this.baseUrl = baseUrl;
    }
}