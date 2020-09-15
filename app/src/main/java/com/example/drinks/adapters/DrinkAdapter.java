package com.example.drinks.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.drinks.R;
import com.example.drinks.activitiesFragments.FullDrinkActivity;
import com.example.drinks.models.Drink;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.ViewHolder> {
    
    private Context context;
    private List<Drink> drinkList;
    private Drink drink;
    
    public DrinkAdapter(Context context, List<Drink> drinkList) {
        this.context = context;
        this.drinkList = drinkList;
    }
    
    @NonNull
    @Override
    public DrinkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_three,parent,false);
        return new ViewHolder(itemView);
    }
    
    @Override
    public void onBindViewHolder(@NonNull DrinkAdapter.ViewHolder holder, int position) {
        Drink drink = drinkList.get(position);
        holder.name.setText(drink.getName());
        holder.id.setText(drink.getId());
        Glide.with(context)
                .load(drink.getImage())
                .override(600, 600)
                .into(holder.image);
    }
    
    @Override
    public int getItemCount() {
        if(drinkList != null) {
            return drinkList.size();
        }
        return 0;
    }
    
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView (R.id.name)
        TextView name;
        @BindView(R.id.id)
        TextView id;
        @BindView(R.id.card_view)
        CardView cardView;
        
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        
        @OnClick(R.id.card_view)
        public void setFullDrink(){
            int position = getAdapterPosition();
            String id = drinkList.get(position).getId();
            Intent intent = new Intent(context,FullDrinkActivity.class);
            intent.putExtra("ID",id);
            context.startActivity(intent);
        }
    }
}
