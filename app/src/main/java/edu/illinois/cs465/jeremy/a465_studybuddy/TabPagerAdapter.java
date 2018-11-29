package edu.illinois.cs465.jeremy.a465_studybuddy;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter {

    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int num_tabs) {
        super(fm);
        this.tabCount = num_tabs;
    }



    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                PeopleFrag tab1 = new PeopleFrag();
                return tab1;
            case 1:
                return null;
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "People";
            case 1:
                return "Requests";
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}

