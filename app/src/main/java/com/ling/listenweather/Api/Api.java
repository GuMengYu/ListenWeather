package com.ling.listenweather.Api;

import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/8.
 */

public interface Api {

    public static final String QUERY_CITYS = "https://cdn.heweather.com/china-city-list.json";
    public static final String GETWEATHER = "https://free-api.heweather.com/v5/weather?";
    public static final String GETPICTURE = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/1/1";


    public void QueryCitys( StringCallback callback);

    public void GetWeather(String city,StringCallback callback);

    public void GetPicture(StringCallback callback);


}
