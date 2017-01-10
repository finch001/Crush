package com.livvy.crush.login.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.livvy.crush.R;
import com.livvy.crush.comm.MVPBaseActivity;
import com.livvy.crush.login.model.User;
import com.livvy.crush.login.presenter.LoginPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends MVPBaseActivity<ILoginView, LoginPresenter> implements ILoginView {

    @Bind(R.id.et_user)
    TextInputEditText userEt;

    @Bind(R.id.password)
    TextInputEditText passwordEt;

    @Bind(R.id.email_sign_in_button)
    Button loginBtn;

    @Bind(R.id.login_progress)
    ProgressBar pb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initListener();

    }

    private void initListener() {
        loginBtn.setOnClickListener(v -> requestLogin());
    }

    private void requestLogin() {
        String userName = userEt.getText().toString().trim();
        String passWord = passwordEt.getText().toString().trim();

        presenter.getUser(new User(userName, passWord));
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void loginFail() {
        showLoading(false);
        Toast.makeText(this, "loginFail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess() {
        showLoading(false);
        Toast.makeText(this, "loginSuccess", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showLoading(boolean isShown) {
        if (isShown) {
            pb.setVisibility(View.VISIBLE);
        } else {
            pb.setVisibility(View.GONE);
        }
    }


}

