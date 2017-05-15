package com.ling.listenweather.Api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ling.listenweather.util.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by Administrator on 2017/5/8.
 */

public class ApiImpl implements Api {

    public final String API_KEY = "95d5078dd7f0478fb42a0365644a4251";

     @Override
    public void QueryCitys(StringCallback callback) {

         OkHttpUtils.get().url(QUERY_CITYS).build().execute(callback);
     }

    @Override
    public void GetWeather(String city,StringCallback callback) {
        String url = GETWEATHER+"city="+city+"&key="+API_KEY;
        OkHttpUtils.get().url(url).build().execute(callback);
    }

    @Override
    public void GetPicture(StringCallback callback) {
        OkHttpUtils.get().url(GETPICTURE).build().execute(callback);

    }
}
