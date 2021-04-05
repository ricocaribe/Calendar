package com.jmrp.calendar;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;


public class CalendarPagerAdapter extends FragmentStatePagerAdapter {

    // This holds all the currently displayable views, in order from left to right.
    private ArrayList<CalendarItemView> mCalendarItemViews = new ArrayList<>();

    public static final String TAG = "CalendarPagerAdapter";

    public CalendarPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mCalendarItemViews.get(position);
    }

    /**
     * Used by ViewPager; can be used by app as well.
     * @return the total number of pages that the ViewPage can display.  This must never be 0.
     */
    @Override
    public int getCount() {
        return mCalendarItemViews.size();
    }

    /**
     * Add "view" at "position" to "views".
     * Returns position of new view.
     * The app should call this to add pages; not used by ViewPager.
     */
    public void addView(CalendarItemView v, int position) {
        mCalendarItemViews.add(position, v);
        notifyDataSetChanged();
        Log.i(TAG, "addView: getCount: "  + getCount());
    }
}
