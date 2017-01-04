package com.livvy.crush.login.view;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.livvy.crush.comm.MVPBaseActivity;
import com.livvy.crush.login.presenter.LoginPresenter;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends MVPBaseActivity<LoginActivity, LoginPresenter> {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

