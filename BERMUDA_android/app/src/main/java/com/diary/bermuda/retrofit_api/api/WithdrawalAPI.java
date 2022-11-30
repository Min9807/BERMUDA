package com.diary.bermuda.retrofit_api.api;

import com.diary.bermuda.dto.CommonResult;

import retrofit2.Call;
import retrofit2.http.POST;

public interface WithdrawalAPI {

    @POST("/api/secession/")
    Call<CommonResult> withdrawal();

}
