package com.jmrp.calendar;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import static com.jmrp.calendar.CalendarItemView.CALENDAR_KEY;


public class CalendarPagerAdapter extends FragmentPagerAdapter {
    // This holds all the currently displayable views, in order from left to right.
    private ArrayList<CalendarItemView> mCalendarItemViews;

    public static final String TAG = "CalendarPagerAdapter";

    private Calendar mCalendar;

    private int mCurrentPosition = -1;

    private ArrayList<Integer> positions = new ArrayList<>();

    private SparseArray<Fragment> registeredFragments = new SparseArray<>();

    public CalendarPagerAdapter(FragmentManager fragmentManager, Calendar calendar) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mCalendar = calendar;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        //(CalendarItemView) getItem(mCurrentPosition-2).getArguments().get(CALENDAR_KEY)
        Log.w(TAG, "getItem: position -> " + position);
        Log.w(TAG, "getItem: positions size" + positions.size());
        CalendarItemView calendarItemView;
        if(mCurrentPosition!=-1){
            if(position<=positions.get(0)-1){
                //Calendar nextCalendar = Calendar.getInstance();
                Calendar previousCalendar = Calendar.getInstance();
                if(registeredFragments.size()>1 && getRegisteredFragment(positions.get(0)).getArguments()!=null){
                    previousCalendar.setTime(((Calendar)getRegisteredFragment(positions.get(0)).getArguments().get(CALENDAR_KEY)).getTime());
                }
                else {
                    previousCalendar = Calendar.getInstance();
                }
                Log.w(TAG, "getItem: mCalendar month -> " + mCalendar.get(Calendar.MONTH));
                Log.w(TAG, "getItem: previousCalendar month ->" + previousCalendar.get(Calendar.MONTH));

                previousCalendar.set(Calendar.MONTH, previousCalendar.get(Calendar.MONTH)-1);
                Log.w(TAG, "getItem: previousCalendar month2 -> " + previousCalendar.get(Calendar.MONTH));
                Log.w(TAG, "getItem: mCalendar month2 -> " + mCalendar.get(Calendar.MONTH));
                mCurrentPosition = position;
                positions.add(0, position);
                calendarItemView = CalendarItemView.newInstance(previousCalendar);
                registeredFragments.put(position, calendarItemView);
                return calendarItemView;
            }
            else {
                //Pillar el calendar del fragment con position mas uno o menos uno y hacer uso de ese en lugar de el instance que por ejemplo apuntaria a junio y estamos creando abril
                Calendar nextCalendar = Calendar.getInstance();
                if(registeredFragments.size()==2){
                    nextCalendar.setTime(((Calendar)getRegisteredFragment(position-1).getArguments().get(CALENDAR_KEY)).getTime());
                }
                else if(registeredFragments.size()>2 && registeredFragments.get(position-1)!=null && getRegisteredFragment(position-1).getArguments()!=null){
                    nextCalendar.setTime(((Calendar)getRegisteredFragment(position-1).getArguments().get(CALENDAR_KEY)).getTime());
                }
                else {
                    nextCalendar = Calendar.getInstance();
                }
                Log.w(TAG, "getItem: mCalendar month -> " + mCalendar.get(Calendar.MONTH));
                Log.w(TAG, "getItem: nextCalendar month ->" + nextCalendar.get(Calendar.MONTH));

                nextCalendar.set(Calendar.MONTH, nextCalendar.get(Calendar.MONTH)+1);

                Log.w(TAG, "getItem: mCalendar month2 -> " + mCalendar.get(Calendar.MONTH));
                Log.w(TAG, "getItem: nextCalendar month2 -> " + nextCalendar.get(Calendar.MONTH));

                mCurrentPosition = position;
                positions.add(position);
                calendarItemView = CalendarItemView.newInstance(nextCalendar);
                registeredFragments.put(position, calendarItemView);
                return calendarItemView;
            }
        }
        else {
            mCurrentPosition = position;
            positions.add(position);
            Log.w(TAG, "getItem: mCalendar month -> " + mCalendar.get(Calendar.MONTH));
            calendarItemView = CalendarItemView.newInstance(mCalendar);
            registeredFragments.put(position, calendarItemView);
            return calendarItemView;
        }
    }

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //registeredFragments.remove(position);
        //positions.remove(position);
        super.destroyItem(container, position, object);
    }

    //-----------------------------------------------------------------------------
    // Used by ViewPager; can be used by app as well.
    // Returns the total number of pages that the ViewPage can display.  This must
    // never be 0.
    @Override
    public int getCount () {
        return Integer.MAX_VALUE;
    }

    //-----------------------------------------------------------------------------
    // Add "view" to right end of "views".
    // Returns the position of the new view.
    // The app should call this to add pages; not used by ViewPager.
    public int addView (CalendarItemView v) {
        return addView (v, mCalendarItemViews.size());
    }

    //-----------------------------------------------------------------------------
    // Add "view" at "position" to "views".
    // Returns position of new view.
    // The app should call this to add pages; not used by ViewPager.
    public int addView (CalendarItemView v, int position)
    {
        mCalendarItemViews.add (position, v);
        notifyDataSetChanged();
        return position;
    }

    //-----------------------------------------------------------------------------
    // Removes "view" from "views".
    // Retuns position of removed view.
    // The app should call this to remove pages; not used by ViewPager.
    public int removeView (ViewPager pager, View v) {
        return removeView (pager, mCalendarItemViews.indexOf (v));
    }

    //-----------------------------------------------------------------------------
    // Removes the "view" at "position" from "views".
    // Retuns position of removed view.
    // The app should call this to remove pages; not used by ViewPager.
    public int removeView (ViewPager pager, int position)
    {
        // ViewPager doesn't have a delete method; the closest is to set the adapter
        // again.  When doing so, it deletes all its views.  Then we can delete the view
        // from from the adapter and finally set the adapter to the pager again.  Note
        // that we set the adapter to null before removing the view from "views" - that's
        // because while ViewPager deletes all its views, it will call destroyItem which
        // will in turn cause a null pointer ref.
        pager.setAdapter (null);
        mCalendarItemViews.remove (position);
        pager.setAdapter (this);

        return position;
    }
}
