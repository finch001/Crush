package com.livvy.crush.comm;

import android.support.v7.app.AppCompatActivity;

import com.livvy.crush.R;

/**
 * Created by Finch on 2016/11/28 0028.
 */

public class BaseActivity extends AppCompatActivity
{


    @Override
    public void onBackPressed()
    {
        super.onBackPressed();

        finish();
        overridePendingTransition(0, R.anim.activity_slide_out_bottom);
    }
}
