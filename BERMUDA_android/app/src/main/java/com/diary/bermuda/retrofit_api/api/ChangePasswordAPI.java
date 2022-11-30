package com.diary.bermuda.retrofit_api.api;

import com.diary.bermuda.dto.CommonResult;
import com.diary.bermuda.dto.UserDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface ChangePasswordAPI {
    @PUT("/api/accounts/password")
    Call<CommonResult> updatePw(@Body UserDto.ChangePwDto changePwDto);
}
