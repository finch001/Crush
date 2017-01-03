package com.livvy.crush.login.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.livvy.crush.R;
import com.livvy.crush.login.model.User;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CommentActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.comment_btn)
    Button commentBtn;

    @Bind(R.id.share_btn)
    Button shareBtn;

    @Bind(R.id.exit_btn)
    Button exitBnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        initListener();


    }

    private void initListener() {
        commentBtn.setOnClickListener(this);
        shareBtn.setOnClickListener(this);
        exitBnt.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.comment_btn:
                User.getInstance().comment(CommentActivity.this);
                break;
            case R.id.share_btn:
                User.getInstance().share(CommentActivity.this);
                break;
            case R.id.exit_btn:
                User.getInstance().setState(User.logoutState);
                break;
        }
    }
}
