package com.diary.bermuda.RetrofitAPI.API;

import com.diary.bermuda.dto.CommonResult;
import com.diary.bermuda.dto.DiaryDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FavoriteAPI {

    @GET("/api/")
    Call<CommonResult> favoriteDiary(@Query("diaryId") int diaryId, @Query("favorite_diary") boolean favorite_diary);

    @GET("/api/")
    Call<CommonResult> favoriteMusic(@Query("musicId") int musicId, @Query("favorite_music") boolean favorite_music);

}
