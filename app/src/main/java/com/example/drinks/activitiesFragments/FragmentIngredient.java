package com.example.drinks.activitiesFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import androidx.fragment.app.Fragment;
import com.example.drinks.R;
import com.example.drinks.adapters.IngredientAdapter;
import com.example.drinks.models.IngredientResult;
import com.example.drinks.models.Ingredient;
import com.example.drinks.utils.Functions;
import com.example.drinks.webservices.ApiInterface;
import com.example.drinks.webservices.ServiceGenerator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentIngredient extends Fragment {
    
    private final String TAG = FragmentIngredient.class.getSimpleName();
    @BindView(R.id.ingredient_progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.frg_ingredient)
    ListView frgIngredient;
    private IngredientAdapter ingredientAdapter;
    private static List<Ingredient> ingredientList = new ArrayList<>();
    private IngredientResult ingredientResult;
    private Unbinder unbinder;
    
    public FragmentIngredient(){}
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredient, container, false);
        unbinder = ButterKnife.bind(this,view);
        ingredientAdapter = new IngredientAdapter(getActivity(),ingredientList);
        frgIngredient.setAdapter(ingredientAdapter);
        showProgressBar(true);
        getDrinks();
        return view;
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    
    @OnItemClick(R.id.frg_ingredient)
    public void onItemClick(int position){
        Ingredient ingredient = ingredientList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("selection", ingredient.getIngredient());
        bundle.putString("key","i");
        FragmentList fragmentList = new FragmentList();
        fragmentList.setArguments(bundle);
        Functions.changeMainFragmentWithBack(getActivity(),fragmentList,"ingredient");
    }
    
    private void getDrinks(){
        ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
        Map<String,String> params = new HashMap<>();
        params.put("i","list");
        Call<IngredientResult> call = apiInterface.getIngredient();
        call.enqueue(new Callback<IngredientResult>() {
            @Override
            public void onResponse(Call<IngredientResult> call, Response<IngredientResult> response) {
                if(response.isSuccessful()){
                    ingredientResult = response.body();
                    int n = ingredientResult.size();
                    ingredientList.clear();
                    for(int i = 0; i < n; i++){
                        if(ingredientResult.getIngredient(i) != null) {
                            ingredientList.add(ingredientResult.getIngredient(i));
                        }
                    }
                    ingredientAdapter.notifyDataSetChanged();
                } else {
                    Log.e(TAG,"onResponse " + response.message());
                }
                showProgressBar(false);
            }
            @Override
            public void onFailure(Call<IngredientResult> call, Throwable t) {
                Log.e(TAG, "onFailure " + t.getMessage());
                showProgressBar(false);
            }
        });
    }
    
    private void showProgressBar(boolean isShow){
        if(isShow){
            progressBar.setVisibility(View.VISIBLE);
            frgIngredient.setVisibility(View.GONE);
            
        } else {
            progressBar.setVisibility(View.GONE);
            frgIngredient.setVisibility(View.VISIBLE);
        }
    }
}
