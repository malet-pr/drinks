package com.example.drinks.models;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class DrinkResult{
    @SerializedName("drinks")
    private List<Drink> drinkList = new ArrayList<>();
    
    public DrinkResult(){}
    
    public DrinkResult(List<Drink> drinkList) {
        this.drinkList = drinkList;
    }
    
    public List<Drink> getDrinkList() {
        return drinkList;
    }
    
    public void setDrinkList(List<Drink> drinkList) {
        this.drinkList = drinkList;
    }
    
    public Drink getDrink(int n) {
        return drinkList.get(n);
    }
    
    public int size() {
        return drinkList.size();
    }

}
