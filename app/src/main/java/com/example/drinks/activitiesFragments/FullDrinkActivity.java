package com.example.drinks.activitiesFragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.drinks.R;
import com.example.drinks.models.FullDrink;
import com.example.drinks.models.FullDrinkResult;
import com.example.drinks.utils.RealmController;
import com.example.drinks.webservices.ApiInterface;
import com.example.drinks.webservices.ServiceGenerator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FullDrinkActivity extends AppCompatActivity {
    
    // TODO: poner un saveButton en FullDrinkActivity
    
    private final String TAG = FullDrinkActivity.class.getSimpleName();
    @BindView(R.id.fulldrink_name)
    TextView fullDrinkName;
    @BindView(R.id.fulldrink_alcohol)
    TextView fullDrinkAlcohol;
    @BindView(R.id.fulldrink_category)
    TextView fullDrinkCategory;
    @BindView(R.id.fulldrink_image)
    ImageView fullDrinkImage;
    @BindView(R.id.fulldrink_ingredients)
    TextView fullDrinkIngredients;
    @BindView(R.id.fulldrink_instructions)
    TextView fullDrinkInstructions;
    @BindView(R.id.fulldrink_progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.frg_fulldrink)
    ScrollView content;
    @BindView(R.id.fab_save)
    FloatingActionButton btnSave;
    @BindDrawable(R.drawable.ic_fab_save)
    Drawable icSave;
    @BindDrawable(R.drawable.ic_fab_delete)
    Drawable icDelete;
    private String id;
    private FullDrinkResult fullDrinkResult;
    private FullDrink fullDrink;
    private Unbinder unbinder;
    private RealmController realmController;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fulldrink_activity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        unbinder = ButterKnife.bind(this);
        Intent intent = getIntent();
        String id = intent.getStringExtra("ID");
        showProgressBar(true);
        getDrinks(id);
        realmController = new RealmController();
        if(realmController.isFullDrinkExist(id)){
            btnSave.setImageDrawable(icDelete);
        }
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
    
    @OnClick(R.id.fab_save)
    public void saveDrink(){
        if(realmController.isFullDrinkExist(fullDrink.getId())) {
            realmController.deleteFullDrink(fullDrink);
            btnSave.setImageDrawable(icSave);
            Toast.makeText(getApplicationContext(), R.string.drinkDeleted, Toast.LENGTH_SHORT).show();
        } else {
            realmController.saveFullDrink(fullDrink);
            btnSave.setImageDrawable(icDelete);
            Toast.makeText(getApplicationContext(), R.string.drinkSaved, Toast.LENGTH_SHORT).show();
        }
    }
    
    private void getDrinks(String id) {
        ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
        Call<FullDrinkResult> call = apiInterface.getFullDrink(id);
        call.enqueue(new Callback<FullDrinkResult>() {
            @Override
            public void onResponse(Call<FullDrinkResult> call, Response<FullDrinkResult> response) {
                if(response.isSuccessful()){
                    fullDrinkResult = response.body();
                    fullDrink = fullDrinkResult.getFullDrink(0);
                    fullDrinkName.setText(fullDrink.getName());
                    fullDrinkAlcohol.setText(fullDrink.getAlcoholic());
                    fullDrinkCategory.setText(fullDrink.getCategory());
                    fullDrinkIngredients.setText(fullDrink.getContentOfDrink());
                    Glide.with(getApplicationContext())
                            .load(fullDrink.getImage())
                            .into(fullDrinkImage);
                    fullDrinkInstructions.setText(fullDrink.getInstructions());
                } else {
                    Log.e(TAG,"onResponse " + response.message());
                }
                showProgressBar(false);
            }
            @Override
            public void onFailure(Call<FullDrinkResult> call, Throwable t) {
                Log.e(TAG, "onFailure " + t.getMessage());
                showProgressBar(false);
            }
        });
    }
    
    private void showProgressBar(boolean isShow){
        if(isShow){
            progressBar.setVisibility(View.VISIBLE);
            content.setVisibility(View.GONE);
            
        } else {
            progressBar.setVisibility(View.GONE);
            content.setVisibility(View.VISIBLE);
        }
    }
}
