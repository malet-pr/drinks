package com.example.drinks.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.drinks.R;
import com.example.drinks.activitiesFragments.FullDrinkActivity;
import com.example.drinks.models.FullDrink;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FullDrinkAdapter extends RecyclerView.Adapter<FullDrinkAdapter.ViewHolder> {
    
    private Context context;
    private ArrayList<String> ids = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();
    private FullDrink fullDrink;
    
    public FullDrinkAdapter() {}
    
    public FullDrinkAdapter(Context context, ArrayList<String> ids, ArrayList<String> names, ArrayList<String> images) {
        this.context = context;
        this.ids = ids;
        this.names = names;
        this.images = images;
    }
    
    @NonNull
    @Override
    public FullDrinkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_three,parent,false);
        return new ViewHolder(itemView);
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(names.get(position));
        holder.id.setText(ids.get(position));
        Glide.with(context)
                .load(images.get(position))
                .override(600, 600)
                .into(holder.image);
    }
    
    @Override
    public int getItemCount() {
        if(ids != null) {
            return ids.size();
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
            String id = ids.get(position);
            Intent intent = new Intent(context, FullDrinkActivity.class);
            intent.putExtra("ID",id);
            context.startActivity(intent);
        }
    }
}
