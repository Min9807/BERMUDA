package com.diary.bermuda.menu_page4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.diary.bermuda.ChangePasswordActiviy;
import com.diary.bermuda.R;
import com.diary.bermuda.first_page.FirstActivity;
import com.diary.bermuda.retrofit_api.api.WithdrawalAPI;
import com.diary.bermuda.retrofit_api.config.RetrofitBuilder;
import com.diary.bermuda.config.AccessTokenSharedPreferences;
import com.diary.bermuda.dto.CommonResult;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SideMenuFragment extends BottomSheetDialogFragment {
    Button logout, withdrawal, changePw, upload_music;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sidemenufragment, container, false);

        withdrawal = (Button) view.findViewById(R.id.withdrawal);
        logout = (Button) view.findViewById(R.id.logout);
        changePw = (Button) view.findViewById(R.id.changePw);
//        upload_music = (Button) view.findViewById(R.id.upload_music);

        changePw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ChangePasswordActiviy.class);
                startActivity(intent);
            }
        });

//        upload_music.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), ChangePasswordActiviy.class);
//                startActivity(intent);
//            }
//        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogLogout();
            }
        });

        withdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogWithdrawal();
            }
        });
        return view;
    }

    //로그아웃 다이얼로그
    private void showDialogLogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity());
        builder.setMessage("로그아웃 하시겠습니까?")
                .setTitle("Logout")
                .setPositiveButton("로그아웃", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AccessTokenSharedPreferences.setAccessToken(null);
                        AccessTokenSharedPreferences.setRefreshToken(null);

                        Intent i = new Intent(getActivity(), FirstActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        getActivity().finish();
                        startActivity(i);
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create().show();
    }

    //회원탈퇴 버튼 show dialog
    private void showDialogWithdrawal() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("회원탈퇴 하시겠습니까?")
                .setTitle("회원탈퇴")
                .setPositiveButton("회원탈퇴", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        secession();                                                         //회원탈퇴 API
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create().show();
    }

    //탈퇴 API 이용해서 탈퇴하고 FirstActivity로 가는 메서드
    private void secession() {
        WithdrawalAPI withdrawalAPI = RetrofitBuilder.getRetrofit().create(WithdrawalAPI.class);        //탈퇴 API

        withdrawalAPI.withdrawal().enqueue(new Callback<CommonResult>() {
            @Override
            public void onResponse(Call<CommonResult> call, Response<CommonResult> response) {
                if(response.isSuccessful()){
                    //이것도 응답에 대한 부분은 StatusDto로 받음
                    CommonResult data = response.body();

                    //201은 성공했을때 코드.
                    if(data.getCode()==201){
                        Intent i = new Intent(getActivity(), FirstActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        getActivity().finish();
                        startActivity(i);
                    }

                    Log.d("test", data.getMsg());

                }
            }

            @Override
            public void onFailure(Call<CommonResult> call, Throwable t) {
                Log.d("tag", "실패입니다.");
            }
        });
    }

}
