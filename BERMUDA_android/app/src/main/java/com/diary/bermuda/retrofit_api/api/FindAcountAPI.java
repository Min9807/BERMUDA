package com.diary.bermuda.retrofit_api.api;

import com.diary.bermuda.dto.CommonResult;
import com.diary.bermuda.dto.SingleResult;

import retrofit2.Call;
import retrofit2.http.GET;
import com.diary.bermuda.dto.UserDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FindAcountAPI {

    @GET("/api/accounts/find-id")
    Call<SingleResult> findAcount(@Query("name") String name, @Query("email") String email);

    @POST("/api/accounts/find-pw")
    Call<CommonResult> findPw(@Body UserDto.FindPwDto findPwDto);
}
