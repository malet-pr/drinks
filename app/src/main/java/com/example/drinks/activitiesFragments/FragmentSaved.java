package com.example.drinks.activitiesFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drinks.R;
import com.example.drinks.adapters.DrinkAdapter;
import com.example.drinks.adapters.FullDrinkAdapter;
import com.example.drinks.models.Drink;
import com.example.drinks.models.FullDrink;
import com.example.drinks.utils.RealmController;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentSaved extends Fragment {
    
    @BindView(R.id.saved_recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.saved_notification)
    TextView notification;
    private FullDrinkAdapter fullDrinkAdapter;
    private List<FullDrink> fullDrinkList = new ArrayList<>();
    private RealmController realmController;
    private ArrayList<String> ids = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();
    Unbinder unbinder;
    
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved, container, false);
        unbinder = ButterKnife.bind(this,view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        getFullDrinks();
        if(fullDrinkList.size() == 0){
            notification.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            notification.setVisibility(View.GONE);
            getDrinks();
            fullDrinkAdapter = new FullDrinkAdapter(getActivity(),ids,names,images);
            recyclerView.setAdapter(fullDrinkAdapter);
        }
        return view;
    }
    
    private void getDrinks() {
        int N = 0;
        if(fullDrinkList != null) N = fullDrinkList.size();
        for(int i = 0; i < N; i++){
            ids.add(fullDrinkList.get(i).getId());
            names.add(fullDrinkList.get(i).getName());
            images.add(fullDrinkList.get(i).getImage());
        }
    }
    
    private void getFullDrinks() {
        RealmController realmController = new RealmController();
        fullDrinkList.addAll(realmController.getFullDrinks());
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

