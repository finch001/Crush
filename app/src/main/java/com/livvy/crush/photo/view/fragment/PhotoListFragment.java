package com.livvy.crush.photo.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.livvy.crush.R;
import com.livvy.crush.comm.BaseFragment;
import com.livvy.crush.comm.entity.Photo;
import com.livvy.crush.photo.presenter.PhotoListPresenter;
import com.livvy.crush.photo.view.PhotoListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Finch on 2016/11/28 0028.
 */

public class PhotoListFragment extends BaseFragment implements PhotoListView {
    private List<Photo> photoList = new ArrayList<>();
    private PhotoListAdapterInner adapter;

    private static final int DEFAULT_PAGE = 1;

    private PhotoListPresenter photoListPresenter;

    @Bind(R.id.list_photos)
    RecyclerView listPhotos;

    public static PhotoListFragment getInstance(String title) {
        PhotoListFragment fragment = new PhotoListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        this.title = bundle.getString(TITLE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listphoto, container, false);
        ButterKnife.bind(this, view);
        initPresenter();
        initData();
        photoListPresenter.getPhoto(DEFAULT_PAGE);
        return view;
    }

    private void initPresenter() {
        photoListPresenter = new PhotoListPresenter(this);
    }

    private void initData() {
        adapter = new PhotoListAdapterInner(getActivity(), photoList);
        listPhotos.setLayoutManager(new LinearLayoutManager(getActivity()));
        listPhotos.setAdapter(adapter);

    }

    @Override
    public void showPhotoInfo(List<Photo> photos) {
        if (photoList == null) {
            return;
        }
        photoList.addAll(photos);
        adapter.notifyDataSetChanged();
    }

    public class PhotoListAdapterInner extends RecyclerView.Adapter<PhotoListAdapterInner.ImageVH> {
        private Context context;

        public PhotoListAdapterInner(Context context, List<Photo> photoList) {

            this.context = context;
        }

        @Override
        public ImageVH onCreateViewHolder(ViewGroup parent, int viewType) {

            ImageVH VH = new ImageVH(LayoutInflater.from(context).inflate(R.layout.item_img, parent, false));
            return VH;
        }

        @Override
        public int getItemCount() {
            return photoList == null ? 0 : photoList.size();
        }


        @Override
        public void onBindViewHolder(ImageVH holder, int position) {
            Photo item = photoList.get(position);
            Glide.with(context).load(item.urls.regular).override(item.getRegularWidth(), item.getRegularHeight()).priority(Priority.HIGH).into(holder.imageView);
        }


        class ImageVH extends RecyclerView.ViewHolder {
            private ImageView imageView;

            public ImageVH(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.img);
            }
        }
    }

}
