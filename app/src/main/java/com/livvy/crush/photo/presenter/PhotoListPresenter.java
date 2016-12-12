package com.livvy.crush.photo.presenter;

import com.livvy.crush.comm.BasePresenter;
import com.livvy.crush.comm.entity.Photo;
import com.livvy.crush.comm.http.HttpMethod;
import com.livvy.crush.photo.view.IPhotoListView;

import java.util.List;

import rx.Subscriber;

/**
 * Created by Finch on 2016/11/28 0028.
 */

public class PhotoListPresenter extends BasePresenter<IPhotoListView> {
    private IPhotoListView photoListView;

    private Subscriber<List<Photo>> subscriber;

    public PhotoListPresenter(IPhotoListView photoListView) {
        this.photoListView = photoListView;
    }

    public void getRemotePhoto(final int page) {
        subscriber = new Subscriber<List<Photo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Photo> photos) {
                if (photoListView != null) {
                    photoListView.showPhotoInfo(photos);
                }
            }
        };

        HttpMethod.getInstance().getPhotos(subscriber, page);

    }

}
