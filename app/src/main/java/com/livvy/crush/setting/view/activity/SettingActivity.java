package com.livvy.crush.setting.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.livvy.crush.R;
import com.livvy.crush.comm.BaseActivity;
import com.livvy.crush.setting.view.fragment.SettingFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Finch on 2016/12/6 0006.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener
{
    private Context context;

    @Bind(R.id.fragment_setting_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        context = SettingActivity.this;
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initToolBar();
    }

    private void initToolBar()
    {
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back_light);
        toolbar.setNavigationOnClickListener(this);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        getFragmentManager().beginTransaction().setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.activity_settings_preferenceContainer, new SettingFragment()).commit();
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            // 表示toolbar的id
            case -1:
                finish();
                overridePendingTransition(0, R.anim.activity_slide_out_bottom);
                break;
        }

    }
}
