package com.diary.bermuda.menu_page4;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.diary.bermuda.R;
import com.diary.bermuda.dto.MusicDto;
import com.diary.bermuda.dto.SingleResult;
import com.diary.bermuda.retrofit_api.api.MusicListAPI;
import com.diary.bermuda.retrofit_api.config.RetrofitBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicPreBright extends Fragment {


    private MusicRecyclerViewAdapter musicRecyclerViewAdapter;

    private Context context;

    private RecyclerView recyclerView;
    private NestedScrollView nestedScrollView;
    private ProgressBar progressBar;

    private int page = 1, limit = 10;

    List<MusicDto.PreviewMusicDto> musicPreviewDtos = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.music_pre_frag, container, false);
        Log.d("test", "test");

        context = container.getContext();

        //리사이클러 뷰 관련 xml 요소들 매핑
        nestedScrollView = (NestedScrollView) view.findViewById(R.id.music_pre_sv);
        recyclerView = (RecyclerView) view.findViewById(R.id.music_pre_rv);
        progressBar = (ProgressBar) view.findViewById(R.id.music_pre_pb);

        musicRecyclerViewAdapter = new MusicRecyclerViewAdapter(getContext(), musicPreviewDtos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(musicRecyclerViewAdapter);

        recyclerView.setHasFixedSize(true);

        getData();

        //리사이클러 뷰를 품고 있는 nestedscrollview 끝에 닿으면 추가로 받아오는 부분
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(@NonNull NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()){
                    page++;
                    progressBar.setVisibility(View.VISIBLE);
                    getData();
                }
            }
        });
        return view;
    }

    //주변 일기장 데이터 받아오는 메소드
    public void getData(){
        String mood;

        Bundle bundle = getArguments();
        if(bundle != null){
            Log.d("test", "test");
            mood = bundle.getString("mood");
        }else
            mood = "All";

        MusicListAPI musicListAPI = RetrofitBuilder.getRetrofit().create(MusicListAPI.class);

        musicListAPI.showMusicPreviewList(mood).enqueue(new Callback<SingleResult>()
        {
            @Override
            public void onResponse(Call<SingleResult> call, Response<SingleResult> response)
            {
                if (response.isSuccessful() && response.body() != null)
                {
                    progressBar.setVisibility(View.GONE);
                    SingleResult result = response.body();

                    parseResult(result);
                }
            }

            @Override
            public void onFailure(Call<SingleResult> call, Throwable t)
            {
                Log.e("에러 : ", t.getMessage());
            }
        });
    }

    private void parseResult(SingleResult result)
    {
        Gson gson = new Gson();
        Type type = new TypeToken<List<MusicDto.PreviewMusicDto>>(){
        }.getType();
        String jsonResult = gson.toJson(result.getData());
        Log.d("test", jsonResult);
        musicPreviewDtos = gson.fromJson(jsonResult, type);

        musicRecyclerViewAdapter = new MusicRecyclerViewAdapter(getContext(), musicPreviewDtos);
        recyclerView.setAdapter(musicRecyclerViewAdapter);
    }
}