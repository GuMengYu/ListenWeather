package com.ling.listenweather.Model.WeatherData;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/5/10.
 */

public class AQI {

    @SerializedName("city")
    public AQICity aqiCity;
    public class AQICity{

        public String aqi;
        public String pm10;
        public String pm25;
        @SerializedName("qlty")
        public String quality;

    }
}
