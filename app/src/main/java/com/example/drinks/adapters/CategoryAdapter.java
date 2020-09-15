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
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryAdapter extends BaseAdapter {
    
    private Context context;
    private List<Category> categoryList;
    
    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }
    
    @Override
    public int getCount() {
        if(categoryList != null) {
            return categoryList.size();
        }
        return 0;
    }
    
    @Override
    public Object getItem(int i) {
        return categoryList.get(i);
    }
    
    @Override
    public long getItemId(int i) {
        return 0;
    }
    
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
        Category category = categoryList.get(i);
        holder.category.setText(category.getCategory());
        return view;
    }
    
    public static class ViewHolder {
        @BindView (R.id.one_item_selection)
        TextView category;
        public ViewHolder(@NonNull View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }
}
