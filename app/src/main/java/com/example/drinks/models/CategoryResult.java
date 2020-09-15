package com.example.drinks.models;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class CategoryResult {
    
    @SerializedName("drinks")
    private List<Category> categoryList = new ArrayList<>();
    
    public CategoryResult(){}
    
    public CategoryResult(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
    
    public List<Category> getCategoryList() { return categoryList; }
    
    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
    
    public Category getCategory(int n) {
        return categoryList.get(n);
    }
    
    public int size() {
        return categoryList.size();
    }
    
}
