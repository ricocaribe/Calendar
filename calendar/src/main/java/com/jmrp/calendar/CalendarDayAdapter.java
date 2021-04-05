package com.jmrp.calendar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Date;

public class CalendarDayAdapter extends BaseAdapter {

    private final Context mContext;

    private final ArrayList<CalendarDay> mCalendarDays;

    public CalendarDayAdapter(Context applicationContext, ArrayList<CalendarDay> calendarDays) {
        this.mContext = applicationContext;
        this.mCalendarDays = calendarDays;
    }

    @Override
    public int getCount() {
        return mCalendarDays.size();
    }

    @Override
    public Object getItem(int i) {
        return mCalendarDays.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return new CalendarDayView(mContext, mCalendarDays.get(i));
    }
}
