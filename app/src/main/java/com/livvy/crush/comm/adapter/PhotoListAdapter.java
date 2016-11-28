package com.livvy.crush.comm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.livvy.crush.R;
import com.livvy.crush.comm.entity.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by finch on 2016/11/28.
 */

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.ImageVH> {

    private List<Photo> photoList;
    private Context context;

    public PhotoListAdapter(Context context, List<Photo> photoList) {
        this.photoList = photoList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return photoList == null ? 0 : photoList.size();
    }

    @Override
    public ImageVH onCreateViewHolder(ViewGroup parent, int viewType) {
            ImageVH VH = new ImageVH(LayoutInflater.from(context).inflate(R.layout.item_img, parent, false));
        return VH;
    }

    @Override
    public void onBindViewHolder(ImageVH holder, int position) {
        Photo item = photoList.get(position);
        Picasso.with(context).load(item.urls.full).into(holder.imageView);

    }

    static class ImageVH extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ImageVH(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img);
        }
    }
}
