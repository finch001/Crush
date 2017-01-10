package com.livvy.crush.comm;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by finch on 2016/12/12.
 */

public abstract class MVPBaseActivity<V, P extends BasePresenter<V>> extends BaseActivity {
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        presenter.attachView((V) this);
        presenter.start();
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
