package com.diary.bermuda.menu_page1;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.diary.bermuda.R;
import com.diary.bermuda.config.BasicInfo;
import com.diary.bermuda.dto.CommonResult;
import com.diary.bermuda.dto.DiaryDto;
import com.diary.bermuda.retrofit_api.api.DiaryListAPI;
import com.diary.bermuda.retrofit_api.config.RetrofitBuilder;

import java.net.URLDecoder;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiaryRecyclerViewAdapter extends RecyclerView.Adapter<DiaryRecyclerViewAdapter.CustomViewHolder> {

    private Context context;
    private List<DiaryDto.DiaryPreviewDto> items;
    MediaPlayer mediaPlayer = new MediaPlayer();
    private boolean playPause;
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView content, name;
        ImageView profile;
        CheckBox checkBox;
//        CheckBox liked;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.content_tv);
            name = itemView.findViewById(R.id.name_tv);
            profile = itemView.findViewById(R.id.iv_profileImage);
            checkBox = itemView.findViewById(R.id.sound);
//            liked = itemView.findViewById(R.id.liked);
        }
    }

    public DiaryRecyclerViewAdapter(Context context, List<DiaryDto.DiaryPreviewDto> items) {

        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public DiaryRecyclerViewAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_main1_item, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryRecyclerViewAdapter.CustomViewHolder holder, int position) {
        DiaryDto.DiaryPreviewDto item = items.get(position);

        holder.checkBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(mediaPlayer != null){
                    mediaPlayer.release();
                }
                if(holder.checkBox.isClickable()) {
                    try {
                        if(holder.checkBox.isChecked()){
                            Toast.makeText(context.getApplicationContext(), "Start",Toast.LENGTH_SHORT).show();
                            mediaPlayer = new MediaPlayer();
                            Log.d("test",item.getMusicId()+"");
//                            mediaPlayer.setDataSource(BasicInfo.baseUrl + "/api/music/" + item.getMusicId()+"");
                            String strDecodeURL = URLDecoder.decode(item.getStoredFilePath());
                            mediaPlayer.setDataSource(strDecodeURL);
                            mediaPlayer.prepare();
                            mediaPlayer.start();
                        }
                        else {
                            Toast.makeText(context.getApplicationContext(), "Stop",Toast.LENGTH_SHORT).show();
                            mediaPlayer.stop();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

//        if(item == null)
//            holder.liked.setChecked(item.getliked());


//        holder.liked.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DiaryListAPI diaryListAPI = RetrofitBuilder.getRetrofit().create(DiaryListAPI.class);
//
//                diaryListAPI.updateLike(holder.liked.isChecked(), item.getDiaryId()).enqueue(new Callback<CommonResult>() {
//                    @Override
//                    public void onResponse(Call<CommonResult> call, Response<CommonResult> response) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<CommonResult> call, Throwable t) {
//
//                    }
//                });
//            }
//        });


//        holder.profile.setImageBitmap(bitmap);
        if (item.getOpen() && !item.getLoginId().equals(item.getUserId())) {
            holder.content.setText("비공개");
        } else {
            holder.content.setText(item.getContent());
        }
        holder.name.setText(item.getNickName());
    }

    @Override
    public int getItemCount() {
        try {
            return this.items.size();
        } catch (NullPointerException e) {
            return 0;
        }
    }
}
