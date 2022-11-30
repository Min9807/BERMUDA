package com.diary.bermuda.first_page;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.diary.bermuda.R;
import com.diary.bermuda.retrofit_api.api.SignUpAPI;
import com.diary.bermuda.retrofit_api.config.RetrofitBuilder;
import com.diary.bermuda.dto.CommonResult;
import com.diary.bermuda.dto.UserDto;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private View view;

    private Button btn_id_duplicate_check, btn_nickname_duplicate_check, btn_register;
    private EditText et_name, et_id, et_passwd, et_passwd_check, et_email, et_nickname;
    private TextView text_check_password;

    private boolean isPasswordSame, isCheckedNickName, isCheckedId;
    private String id, nickname, password, email, name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.register_frag, container, false);
        SignUpAPI signUpAPI = RetrofitBuilder.getRetrofit().create(SignUpAPI.class);    //SignUpAPI

        //button들
        btn_id_duplicate_check = view.findViewById(R.id.btn_id_duplicate_check);
        btn_nickname_duplicate_check = view.findViewById(R.id.btn_nickname_duplicate_check);
        btn_register = view.findViewById(R.id.btn_register);

        //edit_text들
        et_name = view.findViewById(R.id.et_name);
        et_id = view.findViewById(R.id.et_id);
        et_passwd = view.findViewById(R.id.et_passwd);
        et_passwd_check = view.findViewById(R.id.et_passwd_check);
        et_email = view.findViewById(R.id.et_email);
        et_nickname = view.findViewById(R.id.et_nickname);

        //비밀번호 체크 변수
        text_check_password = view.findViewById(R.id.text_check_password);

        //비밀번호랑 비밀번호 확인이 다른 경우 알림 띄움.
        et_passwd_check.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().equals(et_passwd.getText().toString())){
                    text_check_password.setText("비밀번호가 다릅니다. 비밀번호를 확인하세요.");
                    isPasswordSame = false;
                }else{
                    text_check_password.setText("비밀번호가 같습니다.");
                    isPasswordSame = true;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //id
        btn_id_duplicate_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = et_id.getText().toString();                            //입력받은 아이디를 가져옴
                Log.d("test", "여기까진 잘 되네요");
//                Log.d("test", "여기까진 잘 되네요");

                signUpAPI.checkId(id).enqueue(new Callback<CommonResult>() {       //signupAPI 의 checkId를 이용. -> 값은 id
                    @Override
                    public void onResponse(Call<CommonResult> call, Response<CommonResult> response) {
                        if(response.isSuccessful()){
                            //이것도 응답에 대한 부분은 StatusDto로 받음
                            CommonResult data = response.body();

//                            Log.d("te",data.getStatusCode()+"");

                            //201은 성공했을때 코드.
                            if(data.getCode()==201){
                                Toast.makeText(getActivity(),"확인되었습니다.", Toast.LENGTH_SHORT).show();
                                isCheckedId=true;
                            }
                            //그 외의 코드는 성공하지 못한 코드이기 때문에 else로 전부 예외처리
                            else{
                                Toast.makeText(getActivity(),"ID가 중복됩니다.", Toast.LENGTH_SHORT).show();
                                isCheckedId=false;
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
        });

        //id와 똑같이 닉네임 중복 확인
        btn_nickname_duplicate_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nickname = et_nickname.getText().toString();

                Log.d("test", "여기까진 잘 되네요");

                signUpAPI.checkNickName(nickname).enqueue(new Callback<CommonResult>() {
                    @Override
                    public void onResponse(Call<CommonResult> call, Response<CommonResult> response) {
                        if(response.isSuccessful()){

                            CommonResult data = response.body();

                            if(data.getCode()==201){
                                Toast.makeText(getActivity(),"확인되었습니다.", Toast.LENGTH_SHORT).show();
                                isCheckedNickName=true;
                            }
                            else{
                                Toast.makeText(getActivity(),"닉네임이 중복됩니다.", Toast.LENGTH_SHORT).show();
                                isCheckedNickName=false;
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
        });

        //email 형식 맞추기
//        et_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(hasFocus){
//                    Pattern p = Pattern.compile("^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$");
//                    Matcher m = p.matcher((et_email).getText().toString());
//
//                    if(!m.matches()){
//                        Toast.makeText(getActivity(), "Email 형식으로 입력하세요.",Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//            }
//        });

        et_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Pattern p = Pattern.compile("^[_a-zA-Z0-9-]+(.[_a-zA-Z0-9-]+)*@(?:\\w+\\.)+\\w+$");
                    Matcher m = p.matcher((et_email).getText().toString());

                    if(!m.matches()){
                        Toast.makeText(getActivity(), "Email 형식으로 입력하세요.",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


        //마지막으로 회원가입 버튼 눌렀을때
        btn_register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Pattern p = Pattern.compile("^[_a-zA-Z0-9-]+(.[_a-zA-Z0-9-]+)*@(?:\\w+\\.)+\\w+$");
                Matcher m = p.matcher((et_email).getText().toString());

                if(et_name.getText().toString().replace(" ","").equals(""))                     //이름 입력하세요
                    Toast.makeText(getActivity(),"이름을 입력하세요.", Toast.LENGTH_SHORT).show();
                else if(!isCheckedId)
                    Toast.makeText(getActivity(),"ID가 중복체크 완료되지 않았습니다.", Toast.LENGTH_SHORT).show();
                else if(!isPasswordSame)                                                                        //비밀번호 일치하게하세요
                    Toast.makeText(getActivity(),"비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                else if(!isCheckedNickName)                                                                     //닉네임 중복확인하세요
                    Toast.makeText(getActivity(),"닉네임이 중복됩니다.", Toast.LENGTH_SHORT).show();

                else if(!m.matches())
                    Toast.makeText(getActivity(),"이메일 형식으로 입력하세요.",Toast.LENGTH_SHORT).show();

                else if(!m.matches()){
                    Toast.makeText(getActivity(), "Email 형식으로 입력하세요.",Toast.LENGTH_SHORT).show();
                }
                //되었다면 else부터 시작
                else{
                    //signupuserDto 를 선언하고
                    UserDto.SignupUserDto signupUserDto = new UserDto.SignupUserDto();

                    id = et_id.getText().toString();
                    password = et_passwd.getText().toString();
                    nickname = et_nickname.getText().toString();
                    name = et_name.getText().toString();
                    email = et_email.getText().toString();

                    signupUserDto.setUserId(id);        //해당 Dto에 값을 저장
                    signupUserDto.setUserPw(password);
                    signupUserDto.setNickName(nickname);
                    signupUserDto.setUserName(name);
                    signupUserDto.setEmail(email);

                    Log.d("test", "여기까진 잘 되네요");

                    //signupAPI 의 checkId를 이용. -> 값은 id
                    signUpAPI.signup(signupUserDto).enqueue(new Callback<CommonResult>() {
                        @Override
                        public void onResponse(Call<CommonResult> call, Response<CommonResult> response) {
                            if(response.isSuccessful()){

                                CommonResult data = response.body();

                                if(data.getCode()==201){
                                    Toast.makeText(getActivity(),"확인되었습니다.", Toast.LENGTH_SHORT).show();
                                    dismiss();          //dialog 닫기
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


        });

        return view;
    }
}
