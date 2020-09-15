package com.example.drinks.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class IngredientResult {
    @SerializedName("drinks")
    private List<Ingredient> ingredientList = new ArrayList<>();
    
    public IngredientResult(){}
    
    public IngredientResult(List<Ingredient> ingredientList) { this.ingredientList = ingredientList; }
    
    public List<Ingredient> getIngredientList() { return ingredientList; }
    
    public void setIngredientList(List<Ingredient> ingredientList) { this.ingredientList = ingredientList;  }
    
    public Ingredient getIngredient(int n) { return ingredientList.get(n); }
    
    public int size() { return ingredientList.size(); }
}
