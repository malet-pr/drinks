package com.example.drinks.models;

import com.google.gson.annotations.SerializedName;

public class Ingredient {
    @SerializedName("strIngredient1")
    private String ingredient;
    public Ingredient(){}
    
    public Ingredient(String ingredient){
        this.ingredient = ingredient;
    }
    public String getIngredient() {
        return ingredient;
    }
    public void setIngredient(String ingredient){
        this.ingredient = ingredient;
    }
}
