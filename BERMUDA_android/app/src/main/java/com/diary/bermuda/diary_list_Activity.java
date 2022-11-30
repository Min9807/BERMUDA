package com.diary.bermuda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.diary.bermuda.dto.DiaryDto;
import com.diary.bermuda.dto.SingleResult;
import com.diary.bermuda.menu_page1.DiaryRecyclerViewAdapter;
import com.diary.bermuda.retrofit_api.api.DiaryListAPI;
import com.diary.bermuda.retrofit_api.config.RetrofitBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class diary_list_Activity extends AppCompatActivity {

    private DiaryRecyclerViewAdapter diaryRecyclerViewAdapter;

    public String diary_content, content;

    private RecyclerView recyclerView;
    private NestedScrollView nestedScrollView;
    private ProgressBar progressBar;
    List<DiaryDto.DiaryPreviewDto> diaryPreviewDtoList = new ArrayList<>();

    private int page = 1, limit = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_list);

        //리사이클러 뷰 관련 xml 요소들 매핑
        nestedScrollView = (NestedScrollView) findViewById(R.id.scroll_view_a);
        recyclerView = (RecyclerView) findViewById(R.id.frag1_rv_a);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar_a);

        diaryRecyclerViewAdapter = new DiaryRecyclerViewAdapter(getApplicationContext(), diaryPreviewDtoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(diaryRecyclerViewAdapter);

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
    }

    public void getData(){
        DiaryListAPI diaryListAPI = RetrofitBuilder.getRetrofit().create(DiaryListAPI.class);

        diaryListAPI.showMyDiaryList().enqueue(new Callback<SingleResult>()
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
        Type type = new TypeToken<List<DiaryDto.DiaryPreviewDto>>(){
        }.getType();
        String jsonResult = gson.toJson(result.getData());
        Log.d("test", jsonResult);
        diaryPreviewDtoList = gson.fromJson(jsonResult, type);

        diaryRecyclerViewAdapter = new DiaryRecyclerViewAdapter(getApplicationContext(), diaryPreviewDtoList);
        recyclerView.setAdapter(diaryRecyclerViewAdapter);
    }
}