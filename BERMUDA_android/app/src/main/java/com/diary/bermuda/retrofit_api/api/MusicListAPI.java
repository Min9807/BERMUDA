package com.diary.bermuda.retrofit_api.api;


import com.diary.bermuda.dto.SingleResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MusicListAPI {

    @GET("/api/music-list/show")
    Call<SingleResult> showMusicPreviewList(@Query("mood") String mood);
}
