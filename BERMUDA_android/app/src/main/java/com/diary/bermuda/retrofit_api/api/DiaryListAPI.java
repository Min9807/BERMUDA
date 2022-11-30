package com.diary.bermuda.retrofit_api.api;

import com.diary.bermuda.dto.CommonResult;
import com.diary.bermuda.dto.SingleResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DiaryListAPI {

    @GET("/api/diary-list/neighbor")
    Call<SingleResult> showNearDiaryList(@Query("latitude") double latitude, @Query("longitude") double longitude);

    @GET("/api/diary-list/mine")
    Call<SingleResult> showMyDiaryList();

    @POST("/api/diary/like")
    Call<CommonResult> updateLike(@Query("like") boolean liked, @Query("diaryId") Long diaryId);

    @GET("/api/liked/diary")
    Call<SingleResult> showLikedDiaryList();

}
