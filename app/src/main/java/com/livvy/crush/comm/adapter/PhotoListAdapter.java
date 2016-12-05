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

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.ImageVH>
{
    private OnItemOnclickListener listener;
    private List<Photo> photoList;
    private Context context;

    public PhotoListAdapter(Context context, List<Photo> photoList)
    {
        this.photoList = photoList;
        this.context = context;
    }

    @Override
    public int getItemCount()
    {
        return photoList == null ? 0 : photoList.size();
    }

    @Override
    public ImageVH onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new ImageVH(LayoutInflater.from(context).inflate(R.layout.item_img, parent, false));
    }

    @Override
    public void onBindViewHolder(ImageVH holder, int position)
    {
        Photo item = photoList.get(position);
        Glide.with(context).load(item.urls.regular).placeholder(new ColorDrawable(Color.parseColor(item.color))).crossFade()
                .override(item.getRegularWidth(), item.getRegularHeight()).priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(holder.imageView);

        if (listener != null)
        {
            listener.onItemOnclick(position);
        }

    }

    static class ImageVH extends RecyclerView.ViewHolder
    {
        private ImageView imageView;

        public ImageVH(View itemView)
        {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.img);
        }
    }

    public void setOnclickListener(OnItemOnclickListener listener)
    {
        this.listener = listener;
    }

    public interface OnItemOnclickListener
    {
        void onItemOnclick(int position);
    }
}
