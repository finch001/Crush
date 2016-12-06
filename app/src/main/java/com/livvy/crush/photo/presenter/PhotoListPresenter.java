package com.livvy.crush.photo.presenter;

import com.livvy.crush.comm.adapter.PhotoListAdapter;
import com.livvy.crush.comm.entity.Photo;
import com.livvy.crush.comm.http.HttpMethod;
import com.livvy.crush.photo.view.PhotoListView;

import java.util.List;

import rx.Subscriber;

/**
 * Created by Finch on 2016/11/28 0028.
 */

public class PhotoListPresenter
{
    private PhotoListView photoListView;

    private PhotoListAdapter photoListAdapter;

    private Subscriber<List<Photo>> subscriber;

    public PhotoListPresenter(PhotoListView photoListView, PhotoListAdapter adapter)
    {
        this.photoListView = photoListView;
        this.photoListAdapter = adapter;
    }

    public void getRemotePhoto(final int page)
    {
        subscriber = new Subscriber<List<Photo>>()
        {
            @Override
            public void onCompleted()
            {

            }

            @Override
            public void onError(Throwable e)
            {

            }

            @Override
            public void onNext(List<Photo> photos)
            {
                if (photoListView != null)
                {
                    photoListView.showPhotoInfo(photos);
                }
            }
        };

        HttpMethod.getInstance().getPhotos(subscriber, page);

    }

}
