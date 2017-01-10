package com.livvy.crush.comm;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by finch on 2016/12/12.
 */

public abstract class MVPBaseFragment<V, P extends BasePresenter<V>> extends BaseFragment {
    protected P prestener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prestener = createPresenter();
    }

    protected abstract P createPresenter();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        prestener.detachView();
    }
}
