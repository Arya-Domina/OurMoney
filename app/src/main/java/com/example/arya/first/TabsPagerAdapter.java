package com.example.arya.first;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

public class TabsPagerAdapter extends FragmentStatePagerAdapter{
    private Resources resources;
    private static final String ARG_PAGE_NUMBER = "page_number";
    Fragment[] fragments = {new HistoryFragment(), new MyItemsFragment(), new MyFriendsFragment(), new CheckFragment()};

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    private String[] titleTabs;

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = fragments[position];
        Log.v("TabsPagerAdapter", "getItem: " + position);
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_NUMBER, position);
        fragment.setArguments(args);
        return fragment;
//        return PageFragment.newInstance(position +1);
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
