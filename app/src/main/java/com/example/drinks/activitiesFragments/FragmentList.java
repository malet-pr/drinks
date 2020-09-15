package com.example.drinks.activitiesFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.drinks.R;
import com.example.drinks.adapters.DrinkAdapter;
import com.example.drinks.models.Category;
import com.example.drinks.models.Drink;
import com.example.drinks.models.DrinkResult;
import com.example.drinks.webservices.ApiInterface;
import com.example.drinks.webservices.ServiceGenerator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentList extends Fragment {
    private final String TAG = FragmentList.class.getSimpleName();
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private DrinkAdapter drinkAdapter;
    private List<Drink> drinkList = new ArrayList<>();
    private DrinkResult drinkResult;
    private Category category;
    Unbinder unbinder;
    
    public FragmentList(){}
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        unbinder = ButterKnife.bind(this,view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        drinkAdapter = new DrinkAdapter(getActivity(),drinkList);
        recyclerView.setAdapter(drinkAdapter);
        Bundle bundle = getArguments();
        String selection = bundle.getString("selection");
        String key = bundle.getString("key");
        showProgressBar(true);
        getDrinks(key,selection);
        return view;
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    
    private void getDrinks(String key,String selection){
        Map<String,String> params = new HashMap<>();
        params.put(key,selection);
        ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
        Call<DrinkResult> call = apiInterface.getDrinks(params);
        call.enqueue(new Callback<DrinkResult>() {
            @Override
            public void onResponse(Call<DrinkResult> call, Response<DrinkResult> response) {
                if(response.isSuccessful()){
                    drinkResult = response.body();
                    int n = drinkResult.size();
                    for(int i = 0; i < n; i++){
                        if(drinkResult.getDrink(i) != null) {
                            drinkList.add(drinkResult.getDrink(i));
                        }
                    }
                    drinkAdapter.notifyDataSetChanged();
                } else {
                    Log.e(TAG,"onResponse " + response.message());
                }
                showProgressBar(false);
            }
            @Override
            public void onFailure(Call<DrinkResult> call, Throwable t) {
                Log.e(TAG, "onFailure " + t.getMessage());
                showProgressBar(false);
            }
        });
    }
    
    private void showProgressBar(boolean isShow){
        if(isShow){
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

}
