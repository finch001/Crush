package com.livvy.crush.comm.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.livvy.crush.comm.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Finch on 2016/12/5 0005.
 */

public class PhotoViewPagerAdapter extends FragmentPagerAdapter
{
    private List<BaseFragment> fragments = new ArrayList<>();
    private List<String> homeTab = new ArrayList<>();


    public PhotoViewPagerAdapter(FragmentManager fm, List<BaseFragment> fragments, List<String> homeTab)
    {
        super(fm);
        this.fragments = fragments;
        this.homeTab.addAll(homeTab);
    }

    @Override
    public Fragment getItem(int position)
    {
        return fragments.get(position);
    }

    @Override
    public int getCount()
    {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return homeTab.get(position);
    }
}
