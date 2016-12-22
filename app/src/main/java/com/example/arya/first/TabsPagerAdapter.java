package com.example.arya.first;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

public class TabsPagerAdapter extends FragmentStatePagerAdapter{
//    private Resources resources;
    Fragment[] fragments = {new HistoryFragment(), new MyItemsFragment(), new MyFriendsFragment(), new CheckFragment()};

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    private String[] titleTabs;

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "TAB" + (position+1);
    }
}
