package com.ling.listenweather.util;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ling.listenweather.Model.WeatherData.Weather;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/10.
 */

public class HandleResponseUtil {

    public static Weather handleWeatherResponse(String response){
        try{

            JSONObject jsonObject  =new JSONObject(response);

            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather5");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String handlePictureResponse(String response){
        try{

            Gson gson = new Gson();

            Map<String,Object> map = gson.fromJson(response,new TypeToken<Map<String,Object>>(){}.getType());
            List<Map<String,Object>>list = (List<Map<String, Object>>) map.get("results");

            String url = list.get(0).get("url").toString();

            return url;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
