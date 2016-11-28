package com.livvy.crush.photo.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.livvy.crush.R;
import com.livvy.crush.comm.BaseActivity;
import com.livvy.crush.photo.view.fragment.PhotoListFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PhotoListActivity extends BaseActivity {
    private static final String TAG = "PhotoListActivity";

    private static final int DEFAULT_PAGE = 1;

    private Fragment[] fragments = new Fragment[3];

    @Bind(R.id.view_pager)
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        PhotoListFragment fragment;
        for (int i = 0; i < 3; i++) {
            fragment = PhotoListFragment.getInstance("photo " + i);
            fragments[i] = fragment;
        }

        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                PhotoListFragment fragment = (PhotoListFragment) fragments[position];
                return fragment.getTitle();
            }
        };

        viewPager.setAdapter(fragmentPagerAdapter);

    }


}
