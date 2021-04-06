package com.jmrp.calendar;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CalendarItemView extends RelativeLayout {

    public static final String TAG = "CalendarFragmentView";

    private CalendarItemView rlMonthContainer;
    private CalendarMonthView gvContainerMonth;
    private Context mContext;

    public CalendarItemView(Context context) {
        super(context);
        mContext = context;
    }

    public CalendarItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public CalendarItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public void init(){
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        setBackgroundColor(color);
    }

    public void initViews(Context context){
        //Inflate and attach your child XML
        LayoutInflater.from(context).inflate(R.layout.calendar_item, this);
        //Get a reference to the layout where you want children to be placed
        gvContainerMonth = findViewById(R.id.gvContainerMonth);
    }

    /**
     * Set up calendar
     */
    public void setUpCalendar(Calendar mCalendar) {

        //Inflate and attach your child XML
        LayoutInflater.from(mContext).inflate(R.layout.calendar_item, this);
        //Get a reference to the layout where you want children to be placed
        gvContainerMonth = findViewById(R.id.gvContainerMonth);

        //Log.d(TAG, "setUpCalendar: Current calendar month: " + mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, getResources().getConfiguration().locale));

        Log.d(TAG, "setUpCalendar: Current date -> " + mCalendar.getTime());

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

        //Sets month adapter
        //CalendarMonthView calendarMonthView = findViewById(R.id.gvContainerMonth);
        gvContainerMonth.setAdapter(new CalendarDayAdapter(getContext(), calendarDays));

        //Sets click listener for each day in adapter and manage its  events
        gvContainerMonth.setOnItemClickListener((parent, view, position, id) -> Log.d(TAG, "onItemClick -> " + position));
    }
}
