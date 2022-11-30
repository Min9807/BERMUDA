package com.diary.bermuda.menu_page1;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.diary.bermuda.GpsTracker;
import com.diary.bermuda.R;
import com.diary.bermuda.dto.CommonResult;
import com.diary.bermuda.dto.DiaryDto;
import com.diary.bermuda.dto.LocationDto;
import com.diary.bermuda.retrofit_api.api.DiaryAPI;
import com.diary.bermuda.retrofit_api.config.RetrofitBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Main3Fragment extends Fragment {

    public EditText content;
    public boolean btn_open;
    public CheckBox checkBox;
    private Button submit;
    private View view;

    private TextView textView_address;
    private ImageButton showLocationButton;

    public GpsTracker gpsTracker;

    private double latitude, longitude;

    private String diary_content;
    private Boolean diary_open;

    private String address;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        AccessTokenSharedPreferences.init(getActivity());

        view = inflater.inflate(R.layout.fragment_main3, container, false);

        checkBox = (CheckBox) view.findViewById(R.id.btn_open);             //공개, 비공개 여부 받아오기
        submit = (Button) view.findViewById(R.id.submit);
        content = (EditText) view.findViewById(R.id.content);


        //글쓰는 페이지에서 저장을 누르면
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();               //다이아로그를 띄우기
            }
        });


        //현재 위치 가져오기
        textView_address = (TextView) view.findViewById(R.id.locationaddress);
        showLocationButton = (ImageButton) view.findViewById(R.id.ib_location);

        showLocation();

        showLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLocation();
            }
        });

        return view;
    }

    //dialog 띄우고 일기 서버에 보내기 @@@@@@2
    public void showDialog() {
        DiaryAPI diaryAPI = RetrofitBuilder.getRetrofit().create(DiaryAPI.class);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setMessage("저장 하시겠습니까?")

                //저장
                .setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {               //서버, DB에 작성한 글 넘기고 Main3에 작성된 게시글 삭제해야함

                        DiaryDto.DiaryCreateDto diaryCreateDto = new DiaryDto.DiaryCreateDto();
                        LocationDto.RepLocationDto repLocationDto = new LocationDto.RepLocationDto();

                        repLocationDto.setLongitude(longitude);                 //위치값
                        repLocationDto.setLatitude(latitude);
                        repLocationDto.setAddressName(address);

                        diary_open = checkBox.isChecked();
                        diary_content = content.getText().toString();

                        diaryCreateDto.setContent(diary_content);               //일기내용, 비공개,공개 여부
                        diaryCreateDto.setOpen(diary_open);
                        diaryCreateDto.setRepLocationDto(repLocationDto);

                        Log.d("test", "여기까진 잘 되네요");

                        diaryAPI.createDiary(diaryCreateDto).enqueue(new Callback<CommonResult>() {
                            @Override
                            public void onResponse(Call<CommonResult> call, Response<CommonResult> response) {
                                if (response.isSuccessful()) {

                                    CommonResult data = response.body();

                                    if (data.getCode() == 201) {
                                        content.setText(null);
                                        checkBox.setChecked(false);
                                        final InputMethodManager manager = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE) ;  // 키보드 숨기기
                                        Toast.makeText(getActivity(), "확인되었습니다.", Toast.LENGTH_SHORT).show();
                                    }
                                    Log.d("test", data.getMsg());

                                }
//                                getActivity().startActivity(new Intent(getActivity(), diary_list_Activity.class));
                            }

                            @Override
                            public void onFailure(Call<CommonResult> call, Throwable t) {
                                Log.d("tag", "실패입니다.");
                            }
                        });
                    }
                })

                //취소
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        AlertDialog msgDlg = builder.create();
        msgDlg.show();

    }

    public void showLocation() {

        gpsTracker = GpsTracker.getGpsTracker(getContext());

        latitude = gpsTracker.getLatitude();
        longitude = gpsTracker.getLongitude();

        address = getCurrentAddress(latitude, longitude);
        textView_address.setText(address);
    }


    public String getCurrentAddress(double latitude, double longitude) {

        //지오코더... GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());

        List<Address> addresses;

        try {

            addresses = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    7);
        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(getContext(), "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(getContext(), "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";

        }


        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(getContext(), "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";

        }

        Address address = addresses.get(0);
        return address.getAddressLine(0).toString() + "\n";

    }

}