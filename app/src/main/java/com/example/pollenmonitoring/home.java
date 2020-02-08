package com.example.pollenmonitoring;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class home extends FragmentActivity {
    public static Fragment SelectedFragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNav= findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new InfoFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                    switch(menuItem.getItemId()){
                        case R.id.nav_home:
                            SelectedFragment = new InfoFragment();
                            break;

                        case R.id.nav_maps:
                            SelectedFragment = new HeatmapFragment();
                            break;

                        case R.id.nav_info:
                            SelectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_alert:
                            SelectedFragment = new AlertFragment();
                            break;


                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            SelectedFragment).commit();
                    return true;
                }
            };
}
