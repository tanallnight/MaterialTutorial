package com.alexandrepiveteau.library.tutorial;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;

/**
 * Created by Alexandre on 24.04.2015.
 */
public class TutorialViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragments;

    public TutorialViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public void setFragments(List<Fragment> fragments) {
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
