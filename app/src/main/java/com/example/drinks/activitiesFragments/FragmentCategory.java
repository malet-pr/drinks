package com.example.drinks.activitiesFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.core.app.NavUtils;
import androidx.fragment.app.Fragment;
import com.example.drinks.R;
import com.example.drinks.adapters.CategoryAdapter;
import com.example.drinks.models.Category;
import com.example.drinks.models.CategoryResult;
import com.example.drinks.utils.Functions;
import com.example.drinks.webservices.ApiInterface;
import com.example.drinks.webservices.ServiceGenerator;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCategory extends Fragment {
    
    private final String TAG = FragmentCategory.class.getSimpleName();
    @BindView(R.id.category_progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.frg_category)
    ListView frgCategory;
    private CategoryAdapter categoryAdapter;
    private static List<Category> categoryList = new ArrayList<>();
    private CategoryResult categoryResult;
    private Unbinder unbinder;
    
    public FragmentCategory(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        unbinder = ButterKnife.bind(this,view);
        categoryAdapter = new CategoryAdapter(getActivity(),categoryList);
        frgCategory.setAdapter(categoryAdapter);
        showProgressBar(true);
        getDrinks();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    
    @OnItemClick(R.id.frg_category)
    public void onItemClick(int position){
        Category category = categoryList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("selection", category.getCategory());
        bundle.putString("key","c");
        FragmentList fragmentList = new FragmentList();
        fragmentList.setArguments(bundle);
        Functions.changeMainFragmentWithBack(getActivity(),fragmentList,"category");
    }
    
    private void getDrinks(){
        ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
        Call<CategoryResult> call = apiInterface.getCategory();
        call.enqueue(new Callback<CategoryResult>() {
            @Override
            public void onResponse(Call<CategoryResult> call, Response<CategoryResult> response) {
                if(response.isSuccessful()){
                    categoryResult = response.body();
                    int n = categoryResult.size();
                    categoryList.clear();
                    for(int i = 0; i < n; i++){
                        if(categoryResult.getCategory(i) != null) {
                            categoryList.add(categoryResult.getCategory(i));
                        }
                    }
                    categoryAdapter.notifyDataSetChanged();
                } else {
                    Log.e(TAG,"onResponse " + response.message());
                }
                showProgressBar(false);
            }
            @Override
            public void onFailure(Call<CategoryResult> call, Throwable t) {
                Log.e(TAG, "onFailure " + t.getMessage());
                showProgressBar(false);
            }
        });
    }
    
    private void showProgressBar(boolean isShow){
        if(isShow){
            progressBar.setVisibility(View.VISIBLE);
            frgCategory.setVisibility(View.GONE);
            
        } else {
            progressBar.setVisibility(View.GONE);
            frgCategory.setVisibility(View.VISIBLE);
        }
    }
    
}
