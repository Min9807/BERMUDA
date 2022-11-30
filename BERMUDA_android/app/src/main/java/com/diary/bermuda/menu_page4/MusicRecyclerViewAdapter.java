package com.diary.bermuda.menu_page4;

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
import com.diary.bermuda.dto.DiaryDto;
import com.diary.bermuda.dto.MusicDto;

import java.net.URLDecoder;
import java.util.List;

public class MusicRecyclerViewAdapter extends RecyclerView.Adapter<MusicRecyclerViewAdapter.CustomViewHolder> {

    private Context context;
    private List<MusicDto.PreviewMusicDto> items;

    MediaPlayer mediaPlayer = new MediaPlayer();
    private boolean playPause;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView musicPreTitleTv, nickName;
//        ImageView profile;
        CheckBox checkBox;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            musicPreTitleTv = itemView.findViewById(R.id.music_pre_title_tv);
            nickName = itemView.findViewById(R.id.music_pre_nickname_tv);
//            profile = itemView.findViewById(R.id.iv_profileImage);
            checkBox = itemView.findViewById(R.id.music_pre_cb);
        }
    }

    public MusicRecyclerViewAdapter(Context context, List<MusicDto.PreviewMusicDto> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MusicRecyclerViewAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_pre_item, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MusicRecyclerViewAdapter.CustomViewHolder holder, int position) {
        MusicDto.PreviewMusicDto item = items.get(position);

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

        holder.musicPreTitleTv.setText(item.getOriginalFileName());
        holder.nickName.setText(item.getUserNickname());
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
