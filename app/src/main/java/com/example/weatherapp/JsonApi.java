package com.example.weatherapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonApi {

    @GET("weather")
    Call<PostName> getPostName(
            @Query("q") String name,
            @Query("units") String units,
            @Query("APPID") String appid
    );

    @GET("forecast")
    Call<PostList> getPostList(
            @Query("q") String name,
            @Query("units") String units,
            @Query("APPID") String appid
    );
}
