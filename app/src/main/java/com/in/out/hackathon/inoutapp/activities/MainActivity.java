package com.in.out.hackathon.inoutapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.in.out.hackathon.inoutapp.R;
import com.in.out.hackathon.inoutapp.fragments.ParkingPlaceFragment;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbarHome;
    private BottomNavigationView bottomNavigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.item_1:
                    ParkingPlaceFragment parkingPlaceFragment = ParkingPlaceFragment.newInstance();
                    createFragment(parkingPlaceFragment, "ParkingPlaceFragment", false);
                    break;
                case R.id.item_2:
                    break;
                case R.id.item_3:
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();
        ParkingPlaceFragment parkingPlaceFragment = ParkingPlaceFragment.newInstance();
        createFragment(parkingPlaceFragment, "ParkingPlaceFragment", false);

        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

    }


    private void initializeView() {
        toolbarHome = findViewById(R.id.toolbar_home);
        bottomNavigationView = findViewById(R.id.bottom_nav_home);
        setSupportActionBar(toolbarHome);
        getSupportActionBar().setTitle("ParkUp");
        toolbarHome.setTitleTextColor(getResources().getColor(R.color.colorWhite));
    }



    public void createFragment(Fragment fragment, String tag, boolean addToBackStack) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment, tag);
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(tag);
            }
            if (!isFinishing() && !isDestroyed()) {
                fragmentTransaction.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return true;
    }
}
