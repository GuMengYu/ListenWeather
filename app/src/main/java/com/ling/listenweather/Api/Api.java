package com.ling.listenweather.Api;

import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/8.
 */

public interface Api {

    public static final String QUERY_CITYS = "https://cdn.heweather.com/china-city-list.json";

    public void QueryCitys( StringCallback callback);


}
