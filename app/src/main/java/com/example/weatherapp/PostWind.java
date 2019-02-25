package com.example.weatherapp;

import com.google.gson.annotations.SerializedName;

public class PostWind {
    //@SerializedName("speed")
    private float speed;

    @SerializedName("deg")
    private float degree;

    public float getSpeed() {
        return speed;}

    public float getDegree() {
        return degree;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setDegree(float degree) {
        this.degree = degree;
    }
}
