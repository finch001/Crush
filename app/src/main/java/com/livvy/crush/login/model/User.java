package com.livvy.crush.login.model;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import com.livvy.crush.login.view.LoginActivity;

/**
 * Created by finch on 2016/12/13.
 */

public class User implements Parcelable {
    String name;

    String password;

    private UserState state = new LogoutState();

    private static volatile User user;

    private static UserState loginState = new LoginState();

    private static UserState logoutState = new LogoutState();

    private void setState(UserState state) {
        this.state = state;
    }

    public User() {
        name = "";
        password = "";
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public static User getInstance() {
        if (user == null) {
            synchronized (User.class) {
                if (user == null) {
                    user = new User();
                }
            }
        }

        return user;
    }

    /**
     * 登录
     *
     * @param user
     */
    public void login(User user) {
        if (user == null) {
            return;
        } else {
            setUser(user);
        }
    }

    /**
     * 退出登录
     */
    public void logout() {
        if (user == null) {
            return;
        }

        user = null;
        setState(logoutState);
    }

    public void share(Context context) {
        state.share(context);
    }

    public void comment(Context context) {
        state.comment(context);
    }

    private void setUser(User user) {
        //如果校验数据库中是否存在
        this.name = user.name;
        this.password = user.name;

        setState(loginState);
    }


    interface UserState {
        public void share(Context context);

        public void comment(Context context);
    }


    final private static class LoginState implements UserState {
        @Override
        public void share(Context context) {
            Toast.makeText(context, "Share something", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void comment(Context context) {
            Toast.makeText(context, "Comment something", Toast.LENGTH_SHORT).show();
        }
    }

    final private static class LogoutState implements UserState {
        @Override
        public void share(Context context) {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);

        }

        @Override
        public void comment(Context context) {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.password);
    }

    protected User(Parcel in) {
        this.name = in.readString();
        this.password = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
