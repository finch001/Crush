package com.livvy.crush.comm.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.livvy.crush.R;
import com.livvy.crush.comm.entity.Photo;

import java.util.List;

/**
 * Created by finch on 2016/11/28.
 */

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.ImageVH> {
    private OnItemOnclickListener listener;
    private List<Photo> photoList;
    private Context context;

    public PhotoListAdapter(Context context, List<Photo> photoList, OnItemOnclickListener listener) {
        this.photoList = photoList;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return photoList == null ? 0 : photoList.size();
    }

    @Override
    public ImageVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageVH(LayoutInflater.from(context).inflate(R.layout.item_img, parent, false), listener, context);
    }

    @Override
    public void onBindViewHolder(ImageVH holder, int position) {
        Photo item = photoList.get(position);
        holder.setItem(item);
    }

    static class ImageVH extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private Photo photo;
        private Context context;

        public ImageVH(View itemView, final OnItemOnclickListener listener, Context context) {
            super(itemView);
            this.context = context;
            imageView = (ImageView) itemView.findViewById(R.id.img);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemOnclick(photo);
                    }
                }
            });
        }

        public void setItem(Photo photo) {
            this.photo = photo;
            Glide.with(context).load(photo.urls.regular).crossFade().override(photo.getRegularWidth(), photo.getRegularHeight())
                    .centerCrop().placeholder(new ColorDrawable(Color.parseColor(photo.color))).priority(Priority.HIGH)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
        }
    }

    public interface OnItemOnclickListener {
        void onItemOnclick(Photo photo);
    }
}
