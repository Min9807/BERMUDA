package com.diary.bermuda.menu_page4;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.diary.bermuda.R;
import com.google.android.material.navigation.NavigationBarView;

public class musicPreviewActivity extends AppCompatActivity {

    MusicPreAll musicPreAll;
    MusicPreBright musicPreBright;
    MusicPreMid musicPreMid;
    MusicPreDark musicPreDark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_preview);
        musicPreAll = new MusicPreAll();
        musicPreBright = new MusicPreBright();
        musicPreMid = new MusicPreMid();
        musicPreDark = new MusicPreDark();

        Bundle bundle = new Bundle(1);

        getSupportFragmentManager().beginTransaction().replace(R.id.containers, musicPreAll).commit();

        NavigationBarView navigationBarView = findViewById(R.id.music_menu);
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){

                switch(item.getItemId()){
                    case R.id.All:
                        bundle.putString("mood", "all");
                        musicPreAll.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, musicPreAll).commit();
                        return true;
                    case R.id.Bright:
                        bundle.putString("mood", "Bright");
                        musicPreBright.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, musicPreBright).commit();
                        return true;
                    case R.id.Mid:
                        bundle.putString("mood", "Mid");
                        musicPreMid.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, musicPreMid).commit();
                        return true;
                    case R.id.Dark:
                        bundle.putString("mood", "Dark");
                        musicPreDark.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, musicPreDark).commit();

                        return true;
                }
                return false;
            }
        });

    }
}