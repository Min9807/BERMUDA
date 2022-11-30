package com.diary.bermuda;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.diary.bermuda.menu_page1.Main1Fragment;
import com.diary.bermuda.menu_page2.Main2Fragment;
import com.diary.bermuda.menu_page1.Main3Fragment;
import com.diary.bermuda.menu_page4.Main4Fragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    Main1Fragment main1Fragment;
    Main2Fragment main2Fragment;
    Main3Fragment main3Fragment;
    Main4Fragment main4Fragment;

    public static double latitude, longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GpsTracker gpsTracker = new GpsTracker(getApplicationContext());
        latitude = gpsTracker.getLatitude();
        longitude = gpsTracker.getLongitude();


        main1Fragment = new Main1Fragment();
        main2Fragment = new Main2Fragment();
        main3Fragment = new Main3Fragment();
        main4Fragment = new Main4Fragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.containers, main1Fragment).commit();

        NavigationBarView navigationBarView = findViewById(R.id.main_menu);

        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){

                switch(item.getItemId()){
                    case R.id.main1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, main1Fragment).commit();
                        return true;
                    case R.id.main2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, main2Fragment).commit();
                        return true;
                    case R.id.main3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, main3Fragment).commit();
                        return true;
                    case R.id.main4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, main4Fragment).commit();
//                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, main4Fragment).commit();
                        return true;
                }
                return false;
            }
        });

    }
}