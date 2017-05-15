package com.ling.listenweather.Model.WeatherData;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/5/10.
 */

public class Forecast {
    public String date;// 更新时间
    @SerializedName("tmp")
    public Temperature temperature;
    @SerializedName("cond")
    public More more;

    @SerializedName("pop")
    public String dropProbability;//降水概率

    public class Temperature{
        public String max;//最高温度
        public String min;//最低温度
    }

    public class More{
        @SerializedName("txt_d")
        public String info;//天气状况
    }
}
