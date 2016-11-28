package com.livvy.crush.photo.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.livvy.crush.R;
import com.livvy.crush.comm.BaseActivity;
import com.livvy.crush.comm.entity.Photo;
import com.livvy.crush.photo.presenter.PhotoListPresenter;
import com.livvy.crush.photo.view.PhotoListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

public class PhotoListActivity extends BaseActivity implements PhotoListView
{
    private static final String TAG = "PhotoListActivity";

    private Subscriber<Photo> ganHuoSubscriber;

    private PhotoListPresenter listPresenter;

    @Bind(R.id.getPhoto)
    Button getPhotoBtn;

    @OnClick(R.id.getPhoto)
    void onclick()
    {
        listPresenter.getPhoto();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initPresenter();

    }

    private void initPresenter()
    {
        listPresenter = new PhotoListPresenter(this);

    }


    @Override
    public void showPhotoInfo(List<Photo> photos)
    {
        for (Photo item : photos)
        {
            Log.v(TAG, photos.toString());
        }
    }
}
