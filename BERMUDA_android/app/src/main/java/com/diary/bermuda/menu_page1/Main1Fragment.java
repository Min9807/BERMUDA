package com.diary.bermuda.menu_page1;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.diary.bermuda.GpsTracker;
import com.diary.bermuda.R;
import com.diary.bermuda.retrofit_api.api.DiaryListAPI;
import com.diary.bermuda.retrofit_api.config.RetrofitBuilder;
import com.diary.bermuda.dto.DiaryDto;
import com.diary.bermuda.dto.SingleResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main1Fragment extends Fragment {
    private DiaryRecyclerViewAdapter diaryRecyclerViewAdapter;
    public GpsTracker gpsTracker;
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 200;
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private Context context;

    private TextView textView_address;
    private ImageButton showLocationButton;
    private RecyclerView recyclerView;
    private NestedScrollView nestedScrollView;
    private ProgressBar progressBar;

    private int page = 1, limit = 10;

    private double latitude, longitude;

    private String address;

    List<DiaryDto.DiaryPreviewDto> diaryPreviewDtoList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedlnstanceState) {
        super.onCreate(savedlnstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main1, container, false);


        context = container.getContext();

        //리사이클러 뷰 관련 xml 요소들 매핑
        nestedScrollView = (NestedScrollView) view.findViewById(R.id.scroll_view);
        recyclerView = (RecyclerView) view.findViewById(R.id.frag1_rv);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        diaryRecyclerViewAdapter = new DiaryRecyclerViewAdapter(getContext(), diaryPreviewDtoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(diaryRecyclerViewAdapter);

        recyclerView.setHasFixedSize(true);

        if (!checkLocationServicesStatus()) {
               showDialogForLocationServiceSetting();
           }else {
               checkRunTimePermission();
           }

        textView_address = (TextView) view.findViewById(R.id.locationaddress);
        showLocationButton = (ImageButton) view.findViewById(R.id.ib_location);

        showLocation();

        getData();

        //리사이클러 뷰를 품고 있는 nestedscrollview 끝에 닿으면 추가로 받아오는 부분
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(@NonNull NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()){
                    page++;
                    progressBar.setVisibility(View.VISIBLE);
                    getData();
                }
            }
        });

        showLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLocation();
            }
        });
        return view;
    }

    //주변 일기장 데이터 받아오는 메소드
    public void getData(){
        DiaryListAPI diaryListAPI = RetrofitBuilder.getRetrofit().create(DiaryListAPI.class);

        diaryListAPI.showNearDiaryList(latitude, longitude).enqueue(new Callback<SingleResult>()
        {
            @Override
            public void onResponse(Call<SingleResult> call, Response<SingleResult> response)
            {
                if (response.isSuccessful() && response.body() != null)
                {
                    progressBar.setVisibility(View.GONE);
                    SingleResult result = response.body();

                    parseResult(result);
                }
            }

            @Override
            public void onFailure(Call<SingleResult> call, Throwable t)
            {
                Log.e("에러 : ", t.getMessage());
            }
        });
    }

    private void parseResult(SingleResult result)
    {
        Gson gson = new Gson();
        Type type = new TypeToken<List<DiaryDto.DiaryPreviewDto>>(){
        }.getType();
        String jsonResult = gson.toJson(result.getData());
        Log.d("test", jsonResult);
        diaryPreviewDtoList = gson.fromJson(jsonResult, type);

        diaryRecyclerViewAdapter = new DiaryRecyclerViewAdapter(getContext(), diaryPreviewDtoList);
        recyclerView.setAdapter(diaryRecyclerViewAdapter);
    }

    public void showLocation(){

        gpsTracker = GpsTracker.getGpsTracker(getContext());

        latitude = gpsTracker.getLatitude();
        longitude = gpsTracker.getLongitude();

        address = getCurrentAddress();
        textView_address.setText(address);
    }


    /*
     * ActivityCompat.requestPermissions를 사용한 퍼미션 요청의 결과를 리턴받는 메소드입니다.
     */
    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grandResults) {

        if (permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면

            boolean check_result = true;


            // 모든 퍼미션을 허용했는지 체크

            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }


            if (check_result) {
                //위치 값을 가져올 수 있음
                ;
            } else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료

                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), REQUIRED_PERMISSIONS[1])) {

                    Toast.makeText(context, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_LONG).show();


                } else {

                    Toast.makeText(context, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ", Toast.LENGTH_LONG).show();

                }
            }

        }
    }

    void checkRunTimePermission() {

        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION);


        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {

            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)


            // 3.  위치 값을 가져올 수 있음


        } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), REQUIRED_PERMISSIONS[0])) {

                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Toast.makeText(context, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(getActivity(), REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);


            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(getActivity(), REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }

        }

    }

    public String getCurrentAddress() {

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
            Toast.makeText(context, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(context, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";

        }


        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(context, "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";

        }

        Address address = addresses.get(0);
        return address.getAddressLine(0).toString() + "\n";

    }


    //여기부터는 GPS 활성화를 위한 메소드들
    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하실래요?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case GPS_ENABLE_REQUEST_CODE:

                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {

                        Log.d("@@@", "onActivityResult : GPS 활성화 되있음");
                        checkRunTimePermission();
                        return;
                    }
                }

                break;
        }
    }

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

}
