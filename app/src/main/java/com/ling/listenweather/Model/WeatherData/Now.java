package com.ling.listenweather.Model.WeatherData;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/5/10.
 */

public class Now {

    @SerializedName("tmp")
    public String temperature;//温度
    @SerializedName("hum")
    public  String relativeHumidity;//相对湿度

    @SerializedName("cond")
    public More more;

    public Wind wind;

    public class More{
        @SerializedName("txt")
        public String info;//天气状况
    }

    public class Wind{
        @SerializedName("deg")
        public String windDegree;//风向360
        @SerializedName("dir")
        public String windDirection;//风向
        @SerializedName("sc")
        public String windRating;//风力等级
        @SerializedName("spd")
        public String windSpeed;//风速

    }
}
