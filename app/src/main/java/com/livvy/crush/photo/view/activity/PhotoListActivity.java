package com.livvy.crush.photo.view.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.livvy.crush.R;
import com.livvy.crush.comm.BaseActivity;
import com.livvy.crush.comm.BaseFragment;
import com.livvy.crush.comm.adapter.PhotoViewPagerAdapter;
import com.livvy.crush.photo.view.fragment.PhotoListFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PhotoListActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener
{
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView()
    {
        viewPager.setOffscreenPageLimit(PAGE_OFFSET);
        view.setNavigationItemSelectedListener(this);
    }

    private void initData()
    {
        PhotoListFragment fragment;
        for (int i = 0; i < 3; i++)
        {
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
    public boolean onNavigationItemSelected(MenuItem item)
    {
        Snackbar.make(drawerLayout, item.getTitle() + " pressed", Snackbar.LENGTH_LONG).show();
        item.setChecked(true);
        drawerLayout.closeDrawers();
        return true;
    }
}
