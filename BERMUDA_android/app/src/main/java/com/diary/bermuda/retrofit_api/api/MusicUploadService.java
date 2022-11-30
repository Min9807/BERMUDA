package com.diary.bermuda.retrofit_api.api;

import com.diary.bermuda.dto.CommonResult;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface MusicUploadService {

    @Multipart
    @POST("/")
    Call<CommonResult> upload(@Part ("user_id")String id,
                              @Part ("music_title")String music_title,
                              @Part ("music_mood")int music_mood,
                              @Part MultipartBody.Part music);
}
