package com.diary.bermuda.menu_page1;

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

        upload_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChangePasswordActiviy.class);
                startActivity(intent);
            }
        });

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

    //???????????? ???????????????
    private void showDialogLogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity());
        builder.setMessage("???????????? ???????????????????")
                .setTitle("Logout")
                .setPositiveButton("????????????", new DialogInterface.OnClickListener() {
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
                .setNegativeButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create().show();
    }

    //???????????? ?????? show dialog  @@@@@@@@@@@@@ ?????? ???????????????. ?????????.
    private void showDialogWithdrawal() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("???????????? ???????????????????")
                .setTitle("????????????")
                .setPositiveButton("????????????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        secession();                                                         //???????????? API
                    }
                })
                .setNegativeButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create().show();
    }

    //?????? API ???????????? ???????????? FirstActivity??? ?????? ?????????
    private void secession() {
        WithdrawalAPI withdrawalAPI = RetrofitBuilder.getRetrofit().create(WithdrawalAPI.class);        //?????? API

        withdrawalAPI.withdrawal().enqueue(new Callback<CommonResult>() {
            @Override
            public void onResponse(Call<CommonResult> call, Response<CommonResult> response) {
                if(response.isSuccessful()){
                    //????????? ????????? ?????? ????????? StatusDto??? ??????
                    CommonResult data = response.body();

                    //201??? ??????????????? ??????.
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
                Log.d("tag", "???????????????.");
            }
        });
    }

}
