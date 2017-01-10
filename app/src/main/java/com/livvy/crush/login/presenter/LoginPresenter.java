package com.livvy.crush.login.presenter;

import com.livvy.crush.comm.BasePresenter;
import com.livvy.crush.login.model.User;
import com.livvy.crush.login.model.UserServiceImp;
import com.livvy.crush.login.view.ILoginView;

import rx.Subscription;

/**
 * Created by finch on 2016/12/13.
 */

public class LoginPresenter extends BasePresenter<ILoginView> {
    private UserServiceImp userService;

    private ILoginView loginView;

    private Subscription loginSubcription;


    @Override
    public void attachView(ILoginView view) {
        loginView = view;
    }

    @Override
    protected ILoginView getView() {
        return super.getView();
    }

    @Override
    public boolean isViewAttached() {
        return super.isViewAttached();
    }

    @Override
    public void detachView() {
        super.detachView();
        if (!loginSubcription.isUnsubscribed()) {
            loginSubcription.unsubscribe();
        }
    }

    /**
     * 表示可以开始网络读取数据
     */
    @Override
    public void start() {

    }


    public void getUser(User user) {
        loginView.showLoading(true);

        loginSubcription = userService.getUser(user).subscribe(data -> {
            if (data != null) {
                loginView.loginSuccess();
            } else {
                loginView.loginFail();
            }
        });
    }

}
