package com.diary.bermuda.menu_page2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.diary.bermuda.R;

public class Main2Seoul extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main2_seoul, container, false);

        view.findViewById(R.id.tour).setOnClickListener(mListener);
        view.findViewById(R.id.culture).setOnClickListener(mListener);
        view.findViewById(R.id.festival).setOnClickListener(mListener);
        view.findViewById(R.id.leports).setOnClickListener(mListener);


        getChildFragmentManager().beginTransaction().replace(R.id.frag_container, new CategoryFrag()).commit();

        return view;
    }
    private final View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.tour:
                    getChildFragmentManager().beginTransaction().replace(R.id.frag_container, new SeoulTour()).commit();
                    break;
                case R.id.culture:
                    getChildFragmentManager().beginTransaction().replace(R.id.frag_container, new SeoulCulture()).commit();
                    break;
                case R.id.festival:
                    getChildFragmentManager().beginTransaction().replace(R.id.frag_container, new SeoulFestival()).commit();
                    break;
                case R.id.leports:
                    getChildFragmentManager().beginTransaction().replace(R.id.frag_container, new SeoulLeports()).commit();
                    break;
            }
        }
    };
}