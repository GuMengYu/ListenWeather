package com.ling.listenweather.View.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ling.listenweather.Model.WeatherData.Weather;
import com.ling.listenweather.R;


/**
 * Index list view.
 * */

public class IndexListView extends FrameLayout {

    private RelativeLayout forecast;
    private TextView forecastTitle;
    private TextView forecastContent;

    private RelativeLayout wind;
    private TextView windTitle;
    private TextView windContent;

    private RelativeLayout pm;
    private TextView pmTitle;
    private TextView pmContent;

    private RelativeLayout humidity;
    private TextView humidityTitle;
    private TextView humidityContent;

    private RelativeLayout suggestion;
    private TextView suggestionTitle;
    private TextView suggestionContent;

    private RelativeLayout drsg;
    private TextView drsgTitle;
    private TextView drsgContent;

    private RelativeLayout uv;
    private TextView uvTitle;
    private TextView uvContent;

    private RelativeLayout sport;
    private TextView sportTitle;
    private TextView sportContent;

    private RelativeLayout cold;
    private TextView coldTitle;
    private TextView coldContent;

    private RelativeLayout wash_car;
    private TextView wash_carTitle;
    private TextView wash_carContent;

    public IndexListView(Context context) {
        super(context);
        this.initialize();
    }

    public IndexListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initialize();
    }

    public IndexListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initialize();
    }

    // init.

    @SuppressLint("InflateParams")
    private void initialize() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.container_index, null);
        addView(view);

        forecast = (RelativeLayout) findViewById(R.id.container_details_forecast);
        forecastTitle = (TextView) findViewById(R.id.container_details_forecast_title);
        forecastContent = (TextView) findViewById(R.id.container_details_forecast_content);

        wind = (RelativeLayout) findViewById(R.id.container_details_wind);
        windTitle = (TextView) findViewById(R.id.container_details_wind_title);
        windContent = (TextView) findViewById(R.id.container_details_wind_content);

        pm = (RelativeLayout) findViewById(R.id.container_details_pm);
        pmTitle = (TextView) findViewById(R.id.container_details_pm_title);
        pmContent = (TextView) findViewById(R.id.container_details_pm_content);

        humidity = (RelativeLayout) findViewById(R.id.container_details_humidity);
        humidityTitle = (TextView) findViewById(R.id.container_details_humidity_title);
        humidityContent = (TextView) findViewById(R.id.container_details_humidity_content);

        suggestion = (RelativeLayout) findViewById(R.id.container_details_suggestion);
        suggestionTitle = (TextView) findViewById(R.id.container_details_suggestion_title);
        suggestionContent = (TextView) findViewById(R.id.container_details_suggestion_content);

        drsg = (RelativeLayout) findViewById(R.id.container_details_drsg);
        drsgTitle = (TextView) findViewById(R.id.container_details_drsg_title);
        drsgContent = (TextView) findViewById(R.id.container_details_drsg_content);

        uv = (RelativeLayout) findViewById(R.id.container_details_uv);
        uvTitle = (TextView) findViewById(R.id.container_details_uv_title);
        uvContent = (TextView) findViewById(R.id.container_details_uv_content);

        sport = (RelativeLayout) findViewById(R.id.container_details_sport);
        sportTitle = (TextView) findViewById(R.id.container_details_sport_title);
        sportContent = (TextView) findViewById(R.id.container_details_sport_content);

        cold = (RelativeLayout) findViewById(R.id.container_details_cold);
        coldTitle = (TextView) findViewById(R.id.container_details_cold_title);
        coldContent = (TextView) findViewById(R.id.container_details_cold_content);

        wash_car = (RelativeLayout) findViewById(R.id.container_details_wash_car);
        wash_carTitle = (TextView) findViewById(R.id.container_details_wash_car_title);
        wash_carContent = (TextView) findViewById(R.id.container_details_wash_car_content);

    }

    public void setData(Weather weather){

        if (TextUtils.isEmpty(weather.now.wind.windDegree)){
            wind.setVisibility(GONE);
        }else {
            String windDegree = weather.now.wind.windDegree;
            String windDirection = weather.now.wind.windDirection;
            String windRating = weather.now.wind.windRating;
            String windSpeed = weather.now.wind.windSpeed;
            windTitle.setText(windDirection);
            windContent.setText(windRating);
        }
        if (TextUtils.isEmpty(weather.aqi.aqiCity.aqi)){
            pm.setVisibility(GONE);
        }else {
            String qulitily = "空气质量："+weather.aqi.aqiCity.quality +"("+weather.aqi.aqiCity.aqi+")";
            String pm25 = "PM2.5:"+weather.aqi.aqiCity.pm25;
            String pm10 = "PM10:"+weather.aqi.aqiCity.pm10;

            pmTitle.setText(qulitily);
            pmContent.setText(pm25+" "+pm10);
        }

        if (TextUtils.isEmpty(weather.now.relativeHumidity)) {
            humidity.setVisibility(GONE);
        }else{
            humidityContent.setText(weather.now.relativeHumidity);
        }

        if (TextUtils.isEmpty(weather.suggestion.comfort.content)) {
            suggestion.setVisibility(GONE);
        }else{
            suggestionTitle.setText("舒适度指数-"+weather.suggestion.comfort.title);
            suggestionContent.setText(weather.suggestion.comfort.content);
        }

        if (TextUtils.isEmpty(weather.suggestion.dress.content)) {
            drsg.setVisibility(GONE);
        }else{
            drsgTitle.setText("穿衣指数-"+weather.suggestion.dress.title);
            drsgContent.setText(weather.suggestion.dress.content);
        }
        if (TextUtils.isEmpty(weather.suggestion.uv.content)) {
            uv.setVisibility(GONE);
        }else{
            uvTitle.setText("紫外线指数-"+weather.suggestion.uv.title);
            uvContent.setText(weather.suggestion.uv.content);
        }
        if (TextUtils.isEmpty(weather.suggestion.sport.content)) {
            sport.setVisibility(GONE);
        }else{
            sportTitle.setText("运动指数-"+weather.suggestion.sport.title);
            sportContent.setText(weather.suggestion.sport.content);
        }
        if (TextUtils.isEmpty(weather.suggestion.cold.content)) {
            cold.setVisibility(GONE);
        }else{
            coldTitle.setText("感冒指数-"+weather.suggestion.cold.title);
            coldContent.setText(weather.suggestion.cold.content);
        }
        if (TextUtils.isEmpty(weather.suggestion.washCar.content)) {
            wash_car.setVisibility(GONE);
        }else{
            wash_carTitle.setText("洗车指数-"+weather.suggestion.washCar.title);
            wash_carContent.setText(weather.suggestion.washCar.content);
        }


    }

}
