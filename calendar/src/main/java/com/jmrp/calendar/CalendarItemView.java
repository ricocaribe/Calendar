package com.jmrp.calendar;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CalendarItemView extends Fragment {

    public static final String TAG = "CalendarFragmentView";
    public static final String CALENDAR_KEY = "CALENDAR_KEY";

    private CalendarItemView rlMonthContainer;
    private CalendarMonthView gvContainerMonth;
    private Context mContext;
    private int mCurrentMonth;

    // newInstance constructor for creating fragment with arguments
    public static CalendarItemView newInstance(Calendar calendar) {
        CalendarItemView fragmentFirst = new CalendarItemView();
        Bundle args = new Bundle();
        args.putSerializable(CALENDAR_KEY, calendar);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar_item, container, false);
        gvContainerMonth = view.findViewById(R.id.gvContainerMonth);

        if(getArguments()!=null){
            setUpCalendar((Calendar) getArguments().getSerializable(CALENDAR_KEY));
        }
        return view;
    }

    /*public void init(){
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        setBackgroundColor(color);
    }*/

    /**
     * Set up calendar
     */
    public void setUpCalendar(Calendar mCalendar) {
        //Log.d(TAG, "setUpCalendar: Current calendar month: " + mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, getResources().getConfiguration().locale));

        Log.d(TAG, "setUpCalendar: Current date -> " + mCalendar.getTime());

        mCurrentMonth = mCalendar.get(Calendar.MONTH);

        //Current month max days
        int currentMonthMaxDays = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        Log.d(TAG, "setUpCalendar: Current month max days -> " + currentMonthMaxDays);

        //Move calendar backwards to the beginning of the month
        mCalendar.set(Calendar.DAY_OF_MONTH, 1);

        Date startEvaluatedDate = mCalendar.getTime();

        //This gets the day of week range 1-7, Monday - Sunday
        int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);

        //For error if current day is friday and day 1 of week, wu sum 7
        if(dayOfWeek==1) dayOfWeek=8;

        Log.d(TAG, "setUpCalendar: Day of week -> " + dayOfWeek);
        Log.d(TAG, "setUpCalendar: Start evaluated date -> " + mCalendar.getTime());

        //Backtracks to the beginning of current week (Monday)
        mCalendar.add(Calendar.DAY_OF_YEAR, Calendar.MONDAY - dayOfWeek);

        //Now we gonna calculate the number of days from begin of month to begin of week
        Date endEvaluatedDate = mCalendar.getTime();
        Log.d(TAG, "setUpCalendar: End evaluated date -> " + mCalendar.getTime());

        long diff = endEvaluatedDate.getTime() - startEvaluatedDate.getTime();
        long daysBetweenDates = Math.abs(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));

        Log.d(TAG, "setUpCalendar: Days from begin of month to previous -> " + daysBetweenDates);

        //Calculate the days from begin of current month week to the las month day
        int totalDaysCount = currentMonthMaxDays + (int)daysBetweenDates;

        Log.d(TAG, "setUpCalendar: Days count for fill month view -> " + totalDaysCount);

        //Create a list of calendar days for adapter
        ArrayList<CalendarDay> calendarDays = new ArrayList<>();

        //Calendar day element for each day view
        CalendarDay calendarDay;

        for(int i = 0;i<totalDaysCount;i++){
            calendarDay = CalendarDay.from(mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
            calendarDays.add(calendarDay);
            mCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        //Restore to current month
        if(mCurrentMonth!=mCalendar.get(Calendar.MONTH))
            mCalendar.set(Calendar.MONTH, mCalendar.get(Calendar.MONTH)-1);

        //Sets month adapter
        gvContainerMonth.setAdapter(new CalendarDayAdapter(getContext(), calendarDays));

        //Sets click listener for each day in adapter and manage its  events
        gvContainerMonth.setOnItemClickListener((parent, view, position, id) -> Log.d(TAG, "onItemClick -> " + position));
    }
}
