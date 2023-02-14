package com.example.tiktok;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context mContext;
    GestureDetector gestureDetector;
    private List<VideoItem> dataSet;

    MyAdapter(Context mContext){
        this.mContext = mContext;

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView cover;
        VideoView video;
        TextView nickname;
        TextView desc;
        TextView like;
        ImageView avatar;

        MyViewHolder(View view) {
            super(view);
            cover = view.findViewById(R.id.videoCover);
            video = view.findViewById(R.id.videoView);
            nickname = view.findViewById(R.id.name);
            desc = view.findViewById(R.id.description);
            like = view.findViewById(R.id.likecount);
            avatar = view.findViewById(R.id.avatarView);
            cover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Glide.with(v).load(R.drawable.loading).into(cover);
                    video.start();
                    cover.setVisibility(View.INVISIBLE);
                }
            });
            video.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (video.isPlaying())
                        video.pause();
                    else
                        video.start();
                }
            });

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(mContext)
                .setDefaultRequestOptions(
                        new RequestOptions()
                        .frame(1000000)//第一秒（以微秒为单位）
                        .centerCrop()
                        .placeholder(R.drawable.loading)
                        .error(R.drawable.error)
                )
                .load(dataSet.get(position).videoUrl)
                .into(holder.cover);
        holder.cover.bringToFront();
        holder.cover.setVisibility(View.VISIBLE);
        Glide.with(mContext)
                .load(Uri.parse(dataSet.get(position).avatarUrl))
                .into(holder.avatar);
        holder.video.setVideoURI(Uri.parse(dataSet.get(position).videoUrl));
        holder.nickname.setText("@ "+dataSet.get(position).name);
        holder.desc.setText(dataSet.get(position).description);
        holder.like.setText("有"+dataSet.get(position).likes+"人赞了这条视频");
    }

    @Override
    public int getItemCount() {
        return dataSet == null ? 0 : dataSet.size();
    }

    public void setDataSet(List<VideoItem> dataSet){
        this.dataSet = dataSet;
    }

}
