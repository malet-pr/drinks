package com.example.drinks.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.example.drinks.R;
import com.example.drinks.models.Category;
import com.example.drinks.models.Ingredient;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientAdapter extends BaseAdapter {
    
    private Context context;
    private List<Ingredient> ingredientList;
    
    public IngredientAdapter(Context context, List<Ingredient> ingredientList) {
        this.context = context;
        this.ingredientList = ingredientList;
    }
    
    @Override
    public int getCount() {
        if(ingredientList != null){
            return ingredientList.size();
        }
        return 0;
    }
    
    @Override
    public Object getItem(int i) { return ingredientList.get(i); }
    
    @Override
    public long getItemId(int i) { return 0; }
    
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_one,viewGroup,false);
        holder = new ViewHolder(view);
        view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        ButterKnife.bind(this,view);
        Ingredient ingredient = ingredientList.get(i);
        holder.ingredient.setText(ingredient.getIngredient());
        return view;
    }
    
    public static class ViewHolder {
        @BindView (R.id.one_item_selection)
        TextView ingredient;
        public ViewHolder(@NonNull View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }
}
