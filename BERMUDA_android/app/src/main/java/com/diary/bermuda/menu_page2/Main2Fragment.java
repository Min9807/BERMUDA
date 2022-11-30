package com.diary.bermuda.menu_page2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.diary.bermuda.R;


public class Main2Fragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main2, container, false);


        view.findViewById(R.id.seoul).setOnClickListener(mListener);            //서울
        view.findViewById(R.id.gyeonggi).setOnClickListener(mListener);         //경기 + 인천
        view.findViewById(R.id.gangwon).setOnClickListener(mListener);          //강원
        view.findViewById(R.id.chungcheong).setOnClickListener(mListener);      //충청 + 세종 + 대전
        view.findViewById(R.id.gyeongsang).setOnClickListener(mListener);       //경상 + 대구 + 울산 + 부산
        view.findViewById(R.id.jeolla).setOnClickListener(mListener);           //전라 + 광주
        view.findViewById(R.id.jeju).setOnClickListener(mListener);             //제주


        getChildFragmentManager().beginTransaction().replace(R.id.frag_container, new CategoryFrag()).commit();


        return view;
    }


    private final View.OnClickListener mListener = new View.OnClickListener() {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.seoul:
                    getChildFragmentManager().beginTransaction().replace(R.id.frag_container, new Main2Seoul()).commit();
                    break;
                case R.id.gyeonggi:
                    getChildFragmentManager().beginTransaction().replace(R.id.frag_container, new Main2Gyeonggi()).commit();
                    break;
                case R.id.gangwon:
                    getChildFragmentManager().beginTransaction().replace(R.id.frag_container, new Main2Gangwon()).commit();
                    break;
                case R.id.chungcheong:
                    getChildFragmentManager().beginTransaction().replace(R.id.frag_container, new Main2Chungcheong()).commit();
                    break;
                case R.id.gyeongsang:
                    getChildFragmentManager().beginTransaction().replace(R.id.frag_container, new Main2Gyeongsang()).commit();
                    break;
                case R.id.jeolla:
                    getChildFragmentManager().beginTransaction().replace(R.id.frag_container, new Main2Jeolla()).commit();
                    break;
                case R.id.jeju:
                    getChildFragmentManager().beginTransaction().replace(R.id.frag_container, new Main2Jeju()).commit();
                    break;

            }
        }
    };
}
