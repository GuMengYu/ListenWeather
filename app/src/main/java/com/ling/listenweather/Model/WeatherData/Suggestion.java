package com.ling.listenweather.Model.WeatherData;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/5/10.
 */

public class Suggestion {

    @SerializedName("comf")
    public Comfort comfort;

    @SerializedName("cw")
    public WashCar washCar;

    @SerializedName("drsg")
    public Dress dress;

    @SerializedName("flu")
    public Cold cold;

    @SerializedName("sport")
    public Sport sport;

    @SerializedName("uv")
    public Uv uv;

    public class Comfort{
        @SerializedName("brf")
        public String title;
        @SerializedName("txt")
        public String content;
    }

    public class WashCar{
        @SerializedName("brf")
        public String title;
        @SerializedName("txt")
        public String content;
    }

    public class Dress{
        @SerializedName("brf")
        public String title;
        @SerializedName("txt")
        public String content;
    }

    public class Cold{
        @SerializedName("brf")
        public String title;
        @SerializedName("txt")
        public String content;
    }
    public class Sport{
        @SerializedName("brf")
        public String title;
        @SerializedName("txt")
        public String content;
    }

    public class Uv{
        @SerializedName("brf")
        public String title;
        @SerializedName("txt")
        public String content;
    }


}
