package com.example.drinks.activitiesFragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import com.example.drinks.R;
import com.example.drinks.models.FullDrink;
import com.example.drinks.models.FullDrinkResult;
import com.example.drinks.utils.Functions;
import com.example.drinks.webservices.ApiInterface;
import com.example.drinks.webservices.ServiceGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class FragmentLetter extends Fragment {
    
    final String TAG = FragmentLetter.class.getSimpleName();

    RadioButton chLetter;
    @BindView(R.id.btn_clear)
    Button btnClear;
    @BindView(R.id.btn_search)
    Button btnSearch;
    @BindView(R.id.frg_letter)
    EditText getLetter;
    private Unbinder unbinder;
    private FullDrinkResult fullDrinkResult;
    public static ArrayList<String> drinksId = new ArrayList<>();
    public static ArrayList<String> drinksName = new ArrayList<>();
    public static ArrayList<String> drinksImage = new ArrayList<>();
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_letter, container, false);
        unbinder = ButterKnife.bind(this,view);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getChoice();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLetter.setText("");
                getLetter.requestFocus();
            }
        });
        getLetter.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    hideSoftKeyboard();
                    getChoice();
                    return true;
                }
                return false;
            }
        });
        getLetter.requestFocus();
        return view;
    }
    
    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }

    private void getChoice(){
        String choice = "";
        if(getLetter.getText().length() == 0){
            writeSomething();
        } else if(String.valueOf(getLetter.getText()) == null){
            noDrink();
        } else {
            choice = getLetter.getText().toString().toUpperCase();
            getDrinks(choice);
        }
    }
    
    private void getDrinks(String choice) {
        Map<String,String> params = new HashMap<>();
        params.put("f",choice);
        ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
        Call<FullDrinkResult> call = apiInterface.getFullDrink(params);
        call.enqueue(new Callback<FullDrinkResult>() {
            @Override
            public void onResponse(Call<FullDrinkResult> call, Response<FullDrinkResult> response) {
                if(response.isSuccessful()){
                    fullDrinkResult = response.body();
                    int n = fullDrinkResult.size();
                    FullDrink fullDrink;
                    switch (n){
                        case 0:
                            noDrink();
                            break;
                        case 1:
                            fullDrink = fullDrinkResult.getFullDrink(0);
                            String id = fullDrink.getId();
                            Intent intent = new Intent(getContext(),FullDrinkActivity.class);
                            intent.putExtra("ID",id);
                            startActivity(intent);
                            break;
                        default:
                            drinksId.clear();
                            drinksName.clear();
                            drinksImage.clear();
                            for(int i = 0; i < n; i++){
                                fullDrink = fullDrinkResult.getFullDrink(i);
                                drinksId.add(fullDrink.getId());
                                drinksName.add(fullDrink.getName());
                                drinksImage.add(fullDrink.getImage());
                            }
                            FragmentFullDrinkList fragmentFullDrinkList = new FragmentFullDrinkList();
                            Bundle bundle = new Bundle();
                            bundle.putStringArrayList("ID",drinksId);
                            bundle.putStringArrayList("NAME",drinksName);
                            bundle.putStringArrayList("IMAGE",drinksImage);
                            fragmentFullDrinkList.setArguments(bundle);
                            Functions.changeMainFragmentWithBack(getActivity(), fragmentFullDrinkList,"letter");
                            break;
                    }
                    
                } else {
                    Log.e(TAG,"onResponse " + response.message());
                }
            }
            @Override
            public void onFailure(Call<FullDrinkResult> call, Throwable t) {
                Log.e(TAG, "onFailure " + t.getMessage());
            }
        });
    }
    
    private void noDrink() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Could not find a drink starting with that first character");
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                getActivity().recreate();
                MainActivity.drawer.closeDrawer(GravityCompat.START);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    
    private void writeSomething() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("You need to write a character");
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    
}