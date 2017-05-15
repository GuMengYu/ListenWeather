package com.ling.listenweather.Model.WeatherData;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/5/10.
 */

public class Basic {

    @SerializedName("city")
    public String cityName;//城市

    @SerializedName("id")
    public String weatherId;//天气id

    public Update update;

    public class Update{

        @SerializedName("loc")
        public String LocUpdateTime;//本地更新时间

        @SerializedName("utc")
        public String utcUpdateTime;//utc更新时间
    }


}
