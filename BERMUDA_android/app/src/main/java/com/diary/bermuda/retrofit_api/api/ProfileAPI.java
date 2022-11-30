package com.diary.bermuda.retrofit_api.api;

import androidx.annotation.NonNull;

import com.diary.bermuda.dto.CommonResult;
import com.diary.bermuda.dto.SingleResult;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ProfileAPI {
    @Multipart
    @PUT("/api/profile/my-page")
    Call<CommonResult> editProfileImage(@Part @NonNull MultipartBody.Part profileImage);

    @GET("/api/profile/my-page")
    Call<ResponseBody> showProfileImage();

    @GET("/api/profile/mypage")
    Call<SingleResult> getNickname();

}
