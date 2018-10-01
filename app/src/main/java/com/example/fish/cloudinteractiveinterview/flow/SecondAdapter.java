package com.example.fish.cloudinteractiveinterview.flow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.fish.cloudinteractiveinterview.GlideApp;
import com.example.fish.cloudinteractiveinterview.R;

public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.PhotosViewHolder> {
    private Context mContext;
    private SecondModel mSecondModel;

    public SecondAdapter(SecondModel secondModel) {
        mSecondModel = secondModel;
    }

    @NonNull
    @Override
    public PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.view_photos, parent, false);
        return new SecondAdapter.PhotosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosViewHolder holder, int position) {
        holder.setId(String.valueOf(mSecondModel.getApiData().get(position).getId()));
        holder.setTitle(String.valueOf(mSecondModel.getApiData().get(position).getTitle()));
        holder.setPicture(mSecondModel.getApiData().get(position).getThumbnailUrl());
    }

    @Override
    public int getItemCount() {
        return mSecondModel.getApiDataSize();
    }

    class PhotosViewHolder extends RecyclerView.ViewHolder {
        private ImageView mIvUrl;
        private TextView mTvId;
        private TextView mTvTitle;

        public PhotosViewHolder(View itemView) {
            super(itemView);
            mIvUrl = itemView.findViewById(R.id.iv_url);
            mTvId = itemView.findViewById(R.id.tv_id);
            mTvTitle = itemView.findViewById(R.id.tv_title);
        }

        void setPicture(String pictureUrl) {
            GlideApp.with(mContext)
                    .load(pictureUrl)
                    .transition(new DrawableTransitionOptions().crossFade(200))
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(mIvUrl);
        }

        void setId(String id) {
            mTvId.setText(id);
        }

        void setTitle(String title) {
            mTvTitle.setText(title);
        }
    }
}
