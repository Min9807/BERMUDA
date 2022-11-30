package com.diary.bermuda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.diary.bermuda.retrofit_api.api.ChangePasswordAPI;
import com.diary.bermuda.retrofit_api.config.RetrofitBuilder;
import com.diary.bermuda.dto.CommonResult;
import com.diary.bermuda.dto.UserDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActiviy extends AppCompatActivity {

    EditText et_cur_password, et_new_password, et_check_password;
    Button btn_change_pw,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_activiy);

        et_cur_password = findViewById(R.id.et_cur_password);
        et_new_password = findViewById(R.id.et_new_password);
        et_check_password = findViewById(R.id.et_check_password);
        btn_change_pw = findViewById(R.id.btn_change_pw);

        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_change_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangePasswordAPI changePasswordAPI = RetrofitBuilder.getRetrofit().create(ChangePasswordAPI.class);

                String curPw = et_cur_password.getText().toString();
                String newPw = et_new_password.getText().toString();
                String checkPw = et_check_password.getText().toString();

                if(newPw.equals(checkPw)) {

                    UserDto.ChangePwDto changePwDto = new UserDto.ChangePwDto();

                    changePwDto.setCurPw(curPw);
                    changePwDto.setNewPw(newPw);

                    changePasswordAPI.updatePw(changePwDto).enqueue(new Callback<CommonResult>() {
                        @Override
                        public void onResponse(Call<CommonResult> call, Response<CommonResult> response) {
                            if(response.body().getCode() == 201){
                                Toast.makeText(getApplicationContext(), "비밀번호 변경이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                finish();
                            }else if(response.body().getCode() == 17)
                                Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<CommonResult> call, Throwable t) {
                            t.printStackTrace();
                            Toast.makeText(getApplicationContext(), "통신에 에러가 있습니다.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else
                    Toast.makeText(getApplicationContext(), "비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}