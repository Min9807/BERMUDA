package com.diary.bermuda.menu_page4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.diary.bermuda.diary_list_Activity;

import com.diary.bermuda.dto.SingleResult;
import com.diary.bermuda.retrofit_api.api.ProfileAPI;
import com.diary.bermuda.retrofit_api.config.RetrofitBuilder;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import com.diary.bermuda.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main4Fragment extends Fragment {
    private ImageButton  btn_subMenu;
    private ImageView profileView;
    private Button profileButton;
    private TextView profile_name;
    private String nickname;

    private MenuItem nav_view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main4, container, false);
        ProfileAPI profileAPI = RetrofitBuilder.getRetrofit().create(ProfileAPI.class);
        super.onViewCreated(view, savedInstanceState);

        btn_subMenu = (ImageButton) view.findViewById(R.id.btn_subMenu);
        profile_name = (TextView) view.findViewById(R.id.profile_name);



        profileAPI.getNickname().enqueue(new Callback<SingleResult>() {
            @Override
            public void onResponse(Call<SingleResult> call, Response<SingleResult> response) {
                if(response.isSuccessful()){
                    SingleResult data = response.body();

                    nickname = data.getData().toString();

                    profile_name.setText(nickname);
                }
            }

            @Override
            public void onFailure(Call<SingleResult> call, Throwable t) {
                t.printStackTrace();

            }
        });


//        profileView = (ImageView) view.findViewById(R.id.profile_iv);
//        profileButton = (Button) view.findViewById(R.id.profile_btn);

//        profileAPI.showProfileImage().enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
////                Gson gson = new Gson();
////                String jsonResult = gson.toJson(response.body().getData());
////                Log.e("ASDF", jsonResult);
////                Glide.with(getContext())
////                        .load(response.body().getData())
////                        .disallowHardwareConfig()
////                        .into(profileView);
//
////                ProfileImageDto.ShowProfileImageDto showProfileImageDto;
////
////                Gson gson = new Gson();
////                Type type = new TypeToken<ProfileImageDto.ShowProfileImageDto>(){
////                }.getType();
////                String jsonResult = gson.toJson(response.body().getData());
////                Log.d("test", jsonResult);
////                showProfileImageDto = gson.fromJson(jsonResult, type);
//                try {
//                    Glide.with(getContext())
//                            .load(response.body().bytes())
//                            .into(profileView);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.e("test", "error");
//            }
//        });
//
//        profileButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(intent, 0);
//
//            }
//        });

//        holder.profile.setImageBitmap(bitmap);

        view.findViewById(R.id.diary_list).setOnClickListener(mListener);
        view.findViewById(R.id.music_preview).setOnClickListener(mListener);
//        view.findViewById(R.id.liked_list).setOnClickListener(mListener);


        btn_subMenu = (ImageButton) view.findViewById(R.id.btn_subMenu);
        btn_subMenu.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                BottomSheetDialogFragment sideMenuFragment = new SideMenuFragment();
                sideMenuFragment.show(getChildFragmentManager(), "profilefragment");
            }
        });


        return view;
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
//        super.onActivityResult(requestCode, resultCode, data);
//
//        Log.e("test", requestCode + " " + resultCode + "");
//
//        if(requestCode == 0){
//            if(resultCode == Activity.RESULT_OK){
//                try{
//                    Uri uri = data.getData();
//                    File file = new File(getContext().getFilesDir(), uri.getPath());
//                    RequestBody requestFile =
//                            RequestBody.create(
//                                    MediaType.parse("multipart/form-data"),
//                                    file
//                            );
//                    MultipartBody.Part profileImage =
//                            MultipartBody.Part.createFormData("profileImage", file.getName(), requestFile);
//
//                    Log.d("test", uri + " " + file.getPath());
//
//                    profileAPI.editProfileImage(profileImage).enqueue(new Callback<CommonResult>() {
//                        @Override
//                        public void onResponse(Call<CommonResult> call, Response<CommonResult> response) {
//                            Log.d("success", "성공했습니다.");
//                        }
//
//                        @Override
//                        public void onFailure(Call<CommonResult> call, Throwable t) {
//                            Log.d("success", "실패했습니다.");
//                            t.printStackTrace();
//                        }
//                    });
//                    Glide.with(getActivity()).load(uri).into(profileView);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }else if(resultCode == 1){
//
//            }
//        }
//    }

    private final View.OnClickListener mListener = new View.OnClickListener() {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.diary_list:
                    getActivity().startActivity(new Intent(getActivity(), diary_list_Activity.class));
                    break;
                case R.id.music_preview:
                    getActivity().startActivity(new Intent(getActivity(), musicPreviewActivity.class));
                    break;
//                case R.id.liked_list:
//                    getActivity().startActivity(new Intent(getActivity(), LikedListDiary.class));
//                    break;
            }
        }
    };

}