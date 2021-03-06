package com.ling.listenweather.Model.WeatherData;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/5/10.
 */

public class Weather {

    public String status;
    public Basic basic;
    public AQI aqi;
    public Now now;
    public Forecast forecast;
    public Suggestion suggestion;

    @SerializedName("daily_forecast")
    public List<Forecast>  forecastList;
}
