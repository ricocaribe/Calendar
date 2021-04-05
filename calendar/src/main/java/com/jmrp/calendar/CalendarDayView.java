package com.jmrp.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;

public class CalendarDayView extends RelativeLayout {

    /**
     * Yhe current calendar day
     */
    private CalendarDay mCalendarDay;
    /**
     * The context
     */
    private Context mContext;

    public CalendarDayView(Context context) {
        super(context);
    }

    public CalendarDayView(Context context, CalendarDay calendarDay) {
        super(context);
        this.mCalendarDay = calendarDay;
        this.mContext = context;
        initDay();
    }

    public void initDay() {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        View rootView = mInflater.inflate(R.layout.calendar_day, this, true);

        CheckedTextView tvMonth = rootView.findViewById(R.id.tvCalendarDay);
        tvMonth.setText(String.valueOf(mCalendarDay.getDay()));

      /*  boolean disabled = false;
        if(mCalendarDay.isInRange(mCalendarDay.getCalendar().ge.getActualMinimum(Calendar.MONTH),
                mCalendarDay.getCalendar().getActualMaximum(Calendar.MONTH))){

        }*/
    }
}
