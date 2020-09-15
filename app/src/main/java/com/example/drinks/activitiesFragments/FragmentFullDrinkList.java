package com.example.drinks.activitiesFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.drinks.R;
import com.example.drinks.adapters.FullDrinkAdapter;
import com.example.drinks.models.FullDrink;
import com.example.drinks.models.FullDrinkResult;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentFullDrinkList extends Fragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private FullDrinkAdapter fullDrinkAdapter;
    private List<FullDrink> fullDrinkList = new ArrayList<>();
    private FullDrinkResult fullDrinkResult;
    private FullDrink fullDrink;
    Unbinder unbinder;
    
    public FragmentFullDrinkList(){}
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        unbinder = ButterKnife.bind(this,view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        Bundle bundle = getArguments();
        ArrayList<String> ids = bundle.getStringArrayList("ID");
        ArrayList<String> names = bundle.getStringArrayList("NAME");
        ArrayList<String> images = bundle.getStringArrayList("IMAGE");
        fullDrinkAdapter = new FullDrinkAdapter(getActivity(),ids,names,images);
        recyclerView.setAdapter(fullDrinkAdapter);
        return view;
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}

