package com.jmrp.calendar;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class CalendarItemView extends RelativeLayout {

    public static final String TAG = "CalendarFragmentView";

    private Calendar mCalendar;

    public CalendarItemView(Context context) {
        super(context);
    }

    public CalendarItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CalendarItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(Calendar calendar){
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        setBackgroundColor(color);

        mCalendar = calendar;
    }
    /**
     * Set up calendar
     */
    /*public void setUpCalendar() {

        Log.d(TAG, "setUpCalendar: ");

        //Move calendar backwards to the beginning of the week
        ArrayList<CalendarDay> calendarDays = new ArrayList<>();

        //Beginning cell of current month
        int monthBeginningCell = mCalendar.get(Calendar.DAY_OF_WEEK - 2);
        Log.d(TAG, "setUpCalendar: monthBeginningCell: " + monthBeginningCell);

        //Move calendar backwards to the beginning of the week
        mCalendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell);

        //Month max days
        int monthMaxDays = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        Log.d(TAG, "setUpCalendar: monthMaxDays: " + monthMaxDays);

        CalendarDay calendarDay;

        Log.d(TAG, "setUpCalendar: Calendar month: " + mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, getResources().getConfiguration().locale));

        for(int i = 0;i<monthMaxDays;i++){
            calendarDay = CalendarDay.from(mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
            calendarDays.add(calendarDay);
            mCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        CalendarMonthView calendarMonthView = findViewById(R.id.gvContainerMonth);
        calendarMonthView.setAdapter(new CalendarDayAdapter(getContext(), calendarDays));

        //Create an object of CustomAdapter and set Adapter to GridView
        calendarMonthView.setOnItemClickListener((parent, view, position, id) -> Log.d(TAG, "onItemClick: " + position));
    }*/
}
