package com.jmrp.calendar;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.jmrp.calendar.databinding.CalendarMonthBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class CalendarItemView extends Fragment {

    /**
     * Calendar argument key
     */
    public static final String CALENDAR_KEY = "calendar";

    public static final String TAG = "CalendarFragmentView";

    private CalendarMonthBinding calendarMonthBinding;

    private Calendar mCalendar;

    private Context mContext;

    public static CalendarItemView newInstance(Calendar calendar) {
        CalendarItemView calendarItemView = new CalendarItemView();
        Bundle args = new Bundle();
        args.putSerializable(CALENDAR_KEY, calendar);
        calendarItemView.setArguments(args);
        return calendarItemView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        calendarMonthBinding = DataBindingUtil.inflate(inflater, R.layout.calendar_month, container, false);

        if(getArguments()!=null){
            mCalendar = (Calendar) getArguments().get(CALENDAR_KEY);
            setUpCalendar();
        }

        return calendarMonthBinding.getRoot();

    }



    @Override
    public void setMenuVisibility(final boolean visible) {
        if (visible) {
            //Do your stuff here
        }

        super.setMenuVisibility(visible);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * Set up calendar
     */
    public void setUpCalendar() {

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

        Log.d(TAG, "setUpCalendar: Calendar month: " + mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, mContext.getResources().getConfiguration().locale));

        for(int i = 0;i<monthMaxDays;i++){
            calendarDay = CalendarDay.from(mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
            calendarDays.add(calendarDay);
            mCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        //Create an object of CustomAdapter and set Adapter to GridView
        CalendarDayAdapter customAdapter = new CalendarDayAdapter(mContext, calendarDays);
        calendarMonthBinding.gvContainerMonth.setAdapter(customAdapter);

        //Implement setOnItemClickListener event on GridView
        calendarMonthBinding.gvContainerMonth.setOnItemClickListener((parent, view, position, id) -> Log.d(TAG, "onItemClick: " + position));
    }
}
