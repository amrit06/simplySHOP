package com.example.simplyshop;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;



import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private HOME_FRAGMENT homePage;
    private PANTRY_FRAGMENT pantryPage;
    private SHOPLIST_FRAGMENT shopPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homePage = new HOME_FRAGMENT(MainActivity.this);
        pantryPage = new PANTRY_FRAGMENT(MainActivity.this);
        shopPage = new SHOPLIST_FRAGMENT(MainActivity.this);


        Toolbar toolbar = findViewById(R.id.toolbar);
//      setSupportActionBar(toolbar); // this line make toolbar the action bar with name and three dot menu
        drawer = findViewById(R.id.drawer_nav);
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toogle);
        toogle.syncState();


        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homePage).commit();
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_navigation_menu, menu);
         return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navControl = Navigation.findNavController(this, R.id.fragment_container);
        return NavigationUI.navigateUp(navControl, appBarConfig) || super.onSupportNavigateUp();
    }*/

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedfrag = null;
            switch (menuItem.getItemId()){
                case R.id.nav_home:
                    selectedfrag = homePage;
                    break;
                case R.id.nav_pantry:
                    selectedfrag = pantryPage;
                    break;
                case R.id.nav_shoppingList:
                    selectedfrag = shopPage;
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedfrag).commit();

            return true;
        }
    };




}
