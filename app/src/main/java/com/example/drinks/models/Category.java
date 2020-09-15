package com.example.drinks.models;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("strCategory")
    private String category;
    public Category(){}
    
    public Category(String category){
        this.category = category;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category){
        this.category = category;
    }
}
