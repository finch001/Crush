package com.livvy.crush.photo.presenter;

import com.livvy.crush.comm.BasePresenter;
import com.livvy.crush.photo.view.activity.PhotoListActivity;

/**
 * Created by finch on 2017/1/3.
 */

public class PhotoActivityPresenter extends BasePresenter<PhotoListActivity> {


    @Override
    public void attachView(PhotoListActivity view) {
        super.attachView(view);
    }

    @Override
    protected PhotoListActivity getView() {
        return super.getView();
    }

    @Override
    public boolean isViewAttached() {
        return super.isViewAttached();
    }

    @Override
    public void detachView() {
        super.detachView();
    }
}
