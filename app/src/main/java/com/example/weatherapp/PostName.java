package com.example.weatherapp;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.List;

public class PostName {
    @SerializedName("main")
    private PostMain main;

    @SerializedName("wind")
    private PostWind wind;

    @SerializedName("weather")
    private List<Wheather> weather;

    @SerializedName("name")
    private String cityName;

    @SerializedName("dt")
    private long timestamp;

    public Calendar getDate() {
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(timestamp * 1000);
        return date;
    }

    public PostMain getMain() {
        return main;
    }

    public PostWind getWind() {
        return wind;
    }

    public String getCityName() {
        return cityName;
    }

    public void setMain(PostMain main) {
        this.main = main;
    }

    public void setWind(PostWind wind) {
        this.wind = wind;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public List<Wheather> getWeather() {
        return weather;
    }

    public void setWeather(List<Wheather> weather) {
        this.weather = weather;
    }
}
