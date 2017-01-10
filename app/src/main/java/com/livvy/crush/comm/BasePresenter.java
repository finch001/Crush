package com.livvy.crush.comm;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by Finch on 2016/11/28 0028.
 */

public abstract class BasePresenter<T> {
    protected Reference<T> viewRef;

    public void attachView(T view) {
        viewRef = new WeakReference<T>(view);
    }

    protected T getView() {
        return viewRef.get();
    }

    public boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    public void detachView() {

        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }

    public abstract void start();
}
                                                  