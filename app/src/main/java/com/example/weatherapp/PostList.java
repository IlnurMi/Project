package com.example.weatherapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostList {
    @SerializedName("list")
    private List<PostName> items;

    public PostList(List<PostName> items){
        this.items = items;
    }

    public void setItems(List<PostName> items) {
        this.items = items;
    }

    public List<PostName> getItems() {
        return items;
    }
}
