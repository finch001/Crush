package com.livvy.crush.login.view;

/**
 * Created by finch on 2017/1/5.
 * Email zheng0777@gmail.com
 */

public interface ILoginView {

    // 设置数据加载状态
    void loginFail();

    void loginSuccess();

    void showLoading(boolean isShown);
}
