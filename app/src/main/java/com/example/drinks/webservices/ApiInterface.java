package com.example.drinks.webservices;

import com.example.drinks.models.CategoryResult;
import com.example.drinks.models.DrinkResult;
import com.example.drinks.models.FullDrinkResult;
import com.example.drinks.models.IngredientResult;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterface {
    
    @GET("filter.php")
    Call<DrinkResult> getDrinks(@QueryMap Map<String,String> params);
    
    @GET("list.php?c=list")
    Call<CategoryResult> getCategory();
    
    @GET("list.php?i=list")
    Call<IngredientResult> getIngredient();
    
    @GET("lookup.php")
    Call<FullDrinkResult> getFullDrink(@Query("i") String id);
    
    @GET("search.php")
    Call<FullDrinkResult> getFullDrink(@QueryMap Map<String,String> params); // s=nombre  ó  f=inicial
    
}
