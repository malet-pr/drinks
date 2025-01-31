package com.example.drinks.models;

import com.google.gson.annotations.SerializedName;

public class Drink {
    
    @SerializedName("idDrink")
    private String id;
    @SerializedName("strDrink")
    private String name;
    @SerializedName("strDrinkThumb")
    private String image;
    
    public Drink(){}
    
    public Drink(String id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }
    
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getImage() {
        return image;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setImage(String image) {
        this.image = image;
    }
}
