package com.diary.bermuda.retrofit_api.api;


import com.diary.bermuda.dto.DiaryDto;
import com.diary.bermuda.dto.CommonResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DiaryAPI {

    @POST("/api/diary")
    Call<CommonResult> createDiary(@Body DiaryDto.DiaryCreateDto diaryCreateDto);

    @GET("/api/diary/{diary-id}")
    Call<CommonResult> showDiary(@Path("diary-id")String diaryId);

    @PUT("/api/diary/{diary-id}")
    Call<CommonResult> updateDiary(@Path("diary-id")String diaryId);

    @DELETE("/api/diary/{diary-id}")
    Call<CommonResult> deleteDiary(@Path("diary-id")String diaryId);

}
