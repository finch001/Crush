package com.livvy.crush.photo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.livvy.crush.R;
import com.livvy.crush.comm.BaseFragment;
import com.livvy.crush.comm.adapter.PhotoListAdapter;
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

public class PhotoListFragment extends BaseFragment implements PhotoListView, PhotoListAdapter.OnItemOnclickListener
{
    private List<Photo> photoList = new ArrayList<>();
    private PhotoListAdapter adapter;

    private static final int DEFAULT_PAGE = 1;

    private PhotoListPresenter photoListPresenter;

    @Bind(R.id.list_photos)
    RecyclerView photoRecyclerView;

    public static PhotoListFragment getInstance(String title)
    {
        PhotoListFragment fragment = new PhotoListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        this.title = bundle.getString(TITLE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_listphoto, container, false);
        ButterKnife.bind(this, view);
        initPresenter();
        initData();
        return view;
    }


    private void initPresenter()
    {
        photoListPresenter = new PhotoListPresenter(this, adapter);
    }

    private void initData()
    {
        adapter = new PhotoListAdapter(getActivity(), photoList, this);
        photoRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        photoRecyclerView.setAdapter(adapter);
        photoListPresenter.getRemotePhoto(DEFAULT_PAGE);
    }

    @Override
    public void showPhotoInfo(List<Photo> photos)
    {
        if (photoList == null)
        {
            return;
        }
        photoRecyclerView.setVisibility(View.VISIBLE);
        photoList.addAll(photos);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemOnclick(Photo photo)
    {
        if (photo != null)
        {
            Snackbar.make(photoRecyclerView, photo.toString(), Snackbar.LENGTH_SHORT).show();
        }
    }
}
