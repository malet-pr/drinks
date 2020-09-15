package com.example.drinks.utils;

import com.example.drinks.models.Drink;
import com.example.drinks.models.FullDrink;

import java.util.ArrayList;
import java.util.List;
import io.realm.Realm;
import io.realm.RealmResults;

public class RealmController {
    private final Realm realm;
    public RealmController(){
        realm = Realm.getDefaultInstance();
    }
    
    public void saveFullDrink(FullDrink fullDrink){
        realm.beginTransaction();
        realm.copyToRealm(fullDrink);
        realm.commitTransaction();
    }
    
    public void deleteFullDrink(FullDrink fullDrink){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                FullDrink realmFullDrink = realm.where(FullDrink.class).equalTo("id", fullDrink.getId()).findFirst();
                realmFullDrink.deleteFromRealm();
            }
        });
    }
    
    public boolean isFullDrinkExist(String id){
        FullDrink realmFullDrink = realm.where(FullDrink.class).equalTo("id", id).findFirst();
        if(realmFullDrink == null) return false;
        return true;
    }
    
    public List<FullDrink> getFullDrinks(){
        RealmResults<FullDrink> drinks = realm.where(FullDrink.class).findAll();
        drinks = drinks.sort("name");
        return drinks;
    }
    
}
