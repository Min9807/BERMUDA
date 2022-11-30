package com.diary.bermuda.retrofit_api.api;

import com.diary.bermuda.dto.CommonResult;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AutoLoginAPI {

    @GET("/api/auto-login/")
    Call<CommonResult> autoLogin();


}
