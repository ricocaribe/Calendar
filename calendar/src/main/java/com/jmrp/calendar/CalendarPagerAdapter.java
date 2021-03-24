package com.jmrp.calendar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class CalendarPagerAdapter extends FragmentPagerAdapter {
    public CalendarPagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Override
    public int getCount() {
        return 12;
    }

    @Override
    public Fragment getItem(int position) {
        return CalendarFragmentView.newInstance(position);
    }
}
