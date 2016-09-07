package com.hzq.okhttp;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hezhiqiang on 16/7/21.
 */
public class OkhttpManager {

    public static final String URL= "https://raw.github.com/square/okhttp/master/README.md";
    public static final long cacheSize = 50*1024*1024;
    Cache cache = new Cache(new File("cache.tmp"),cacheSize);
    OkHttpClient okHttpClient = new OkHttpClient();


    public void setCache(){
        okHttpClient = new OkHttpClient.Builder()
                .cache(cache).build();
    }
    public void get(String url) throws IOException{
        Request build = new Request.Builder().url(url).cacheControl(CacheControl.FORCE_CACHE).build();
//        Response response = okHttpClient.newCall(build).execute();
        okHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
}
