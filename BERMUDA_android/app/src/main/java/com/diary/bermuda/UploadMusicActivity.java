package com.diary.bermuda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UploadMusicActivity extends AppCompatActivity {

    EditText music_title;
    Spinner music_array;

    String music_select;
    int music_mood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_music);

        music_title=(EditText) findViewById(R.id.music_title);
        music_array = (Spinner) findViewById(R.id.music_array);

        music_array.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                music_select=music_array.getSelectedItem().toString();
                if(music_select.equals("밝음"))
                    music_mood=0;
                else if(music_select.equals("중간"))
                    music_mood=1;
                else if(music_select.equals("어두움"))
                    music_mood=2;
                else{
                    Toast.makeText(getApplicationContext(),"제대로 선택하세요.",Toast.LENGTH_SHORT).show();
                    Log.d("음악분위기", String.valueOf(music_mood));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
//                Toast.makeText(getApplicationContext(),"제대로 선택하세요.",Toast.LENGTH_SHORT).show();

            }
        });

    }
}