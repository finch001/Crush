package com.livvy.crush.login.model;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.livvy.crush.login.view.LoginActivity;

/**
 * Created by finch on 2016/12/13.
 */

public class User {
    String name;

    String password;

    private UserState state = new LogoutState();

    private static volatile User user;

    public static UserState loginState = new LoginState();

    public static UserState logoutState = new LogoutState();

    public void setState(UserState state) {
        this.state = state;
    }

    private User() {
        name = "";
        password = "";
    }

    public void setUser(User user) {
        this.name = user.name;
        this.password = user.name;
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

    public void share(Context context) {
        state.share(context);
    }

    public void comment(Context context) {
        state.comment(context);
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

}
