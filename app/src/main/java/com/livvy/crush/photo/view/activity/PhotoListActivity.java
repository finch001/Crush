package com.livvy.crush.photo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.livvy.crush.R;
import com.livvy.crush.comm.BaseFragment;
import com.livvy.crush.comm.MVPBaseActivity;
import com.livvy.crush.comm.adapter.PhotoViewPagerAdapter;
import com.livvy.crush.photo.presenter.PhotoActivityPresenter;
import com.livvy.crush.photo.view.fragment.PhotoListFragment;
import com.livvy.crush.setting.view.activity.SettingActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class PhotoListActivity extends MVPBaseActivity<PhotoListActivity, PhotoActivityPresenter> implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private static final String TAG = "PhotoListActivity";

    private static final int DEFAULT_PAGE = 1;

    private static final int PAGE_OFFSET = 3;

    private List<BaseFragment> fragments = new ArrayList<>(PAGE_OFFSET);

    @Bind(R.id.view_pager)
    ViewPager viewPager;

    @Bind(R.id.navigation_view)
    NavigationView view;

    @Bind(R.id.activity_main)
    DrawerLayout drawerLayout;

    @Bind(R.id.fragment_home_tabLayout)
    TabLayout tabLayout;

    @Bind(R.id.fragment_home_toolbar)
    Toolbar toolbar;

    View headMenuLayout;
    View loginAfterLayout;

    CircleImageView userAvater;

    TextView userNameTv;

    Button loginBtn;

    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolBar();
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        loginAfterLayout.setOnClickListener(this);
        userAvater.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
    }

    @Override
    protected PhotoActivityPresenter createPresenter() {
        return new PhotoActivityPresenter();
    }

    private void initToolBar() {
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_menu_light);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);

        mDrawerToggle.syncState();
        drawerLayout.addDrawerListener(mDrawerToggle);
    }

    private void initView() {
        viewPager.setOffscreenPageLimit(PAGE_OFFSET);
        view.setNavigationItemSelectedListener(this);

        headMenuLayout = view.getHeaderView(0);
        userAvater = (CircleImageView) headMenuLayout.findViewById(R.id.user_avator_iv);
        userNameTv = (TextView) headMenuLayout.findViewById(R.id.user_name_tv);
        loginBtn = (Button) headMenuLayout.findViewById(R.id.login_btn);
        loginAfterLayout = headMenuLayout.findViewById(R.id.login_success_layout);
    }

    private void initData() {
        PhotoListFragment fragment = null;
        for (int i = 0; i < 3; i++) {
            fragment = PhotoListFragment.getInstance("photo " + i);
            fragments.add(fragment);
        }

        String[] homeTabs = getResources().getStringArray(R.array.home_tabs);
        List<String> tabList = new ArrayList<>();
        Collections.addAll(tabList, homeTabs);

        PhotoViewPagerAdapter pagerAdapter = new PhotoViewPagerAdapter(getSupportFragmentManager(), fragments, tabList);
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Snackbar.make(drawerLayout, item.getTitle() + " pressed", Snackbar.LENGTH_LONG).show();
        item.setChecked(true);
        drawerLayout.closeDrawers();

        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(PhotoListActivity.this, SettingActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                break;

            case R.id.login_success_layout:
                // 跳转到个人主页


                break;
            default:
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        intent.getData().getAuthority();
    }
}
