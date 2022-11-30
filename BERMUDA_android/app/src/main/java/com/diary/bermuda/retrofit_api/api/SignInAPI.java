package com.diary.bermuda.retrofit_api.api;

import com.diary.bermuda.dto.CommonResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SignInAPI {

    @GET("/api/signin")
    Call<CommonResult> getLogin(@Query("id") String id, @Query("password") String password);

}
