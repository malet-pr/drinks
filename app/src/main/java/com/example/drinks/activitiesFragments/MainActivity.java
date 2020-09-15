package com.example.drinks.activitiesFragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.example.drinks.R;
import com.example.drinks.utils.Functions;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    
    private Toolbar toolbar;
    public static DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private FragmentManager fragmentManager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.drawer_open, R.string.drawer_close);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        drawer.addDrawerListener(drawerToggle);
        fragmentManager = getSupportFragmentManager();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);   // Para poner íconos de otros colores
        drawer.openDrawer(GravityCompat.START, true);
    }
    
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        switch (item.getItemId()) {
            case R.id.nav_name:
                Functions.changeMainFragmentWithBack(MainActivity.this,new FragmentName(),"name");
                break;
            case R.id.nav_letter:
                Functions.changeMainFragmentWithBack(MainActivity.this,new FragmentLetter(),"letter");
                break;
            case R.id.nav_category:
                Functions.changeMainFragmentWithBack(MainActivity.this,new FragmentCategory(),"category");
                break;
            case R.id.nav_ingredient:
                Functions.changeMainFragmentWithBack(MainActivity.this,new FragmentIngredient(),"ingredient");
                break;
            case R.id.nav_saved:
                Functions.changeMainFragmentWithBack(MainActivity.this,new FragmentSaved(),"saved");
                break;
            case R.id.nav_close:
                closeMessage();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        int backStackCount = getSupportFragmentManager().getBackStackEntryCount();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(backStackCount == 1){
            drawer.openDrawer(GravityCompat.START);
        } else if (backStackCount == 0) {
            closeMessage();
        }else {
            super.onBackPressed();
        }
    }
    
    public void closeMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Confirm Action");
        builder.setMessage("Do you really want to exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                moveTaskToBack(true);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                drawer.openDrawer(GravityCompat.START);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    
}