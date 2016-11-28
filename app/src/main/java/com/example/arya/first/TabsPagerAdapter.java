package com.example.arya.first;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter{
    private Resources resources;

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    private String[] titleTabs;

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position +1);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "TAB" + (position+1);
//        titleTabs = R.array.tabs;
//        String[] names = getResources().getStringArray(R.array.tabs);
//        return names[position];
    }
}
