package com.example.drinks.models;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class FullDrinkResult {
    @SerializedName("drinks")
    private List<FullDrink> fullDrinkList = new ArrayList<>();
    
    public FullDrinkResult(){}
    
    public FullDrinkResult(List<FullDrink> fullDrinkList) {
        this.fullDrinkList = fullDrinkList;
    }
    
    public List<FullDrink> getFullDrinkList() { return fullDrinkList; }
    
    public void setFullDrinkList(List<FullDrink> fullDrinkList) {
        this.fullDrinkList = fullDrinkList;
    }
    
    public FullDrink getFullDrink(int n) {
        return fullDrinkList.get(n);
    }
    
    public int size() {
        if(fullDrinkList != null) {
            return fullDrinkList.size();
        }
        return 0;
    }

}
