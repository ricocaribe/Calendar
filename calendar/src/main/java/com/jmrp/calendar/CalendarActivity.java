package com.jmrp.calendar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jmrp.calendar.databinding.ActivityCalendarBinding;

public class CalendarActivity extends AppCompatActivity {

    /**
     * The constant TAG
     */
    public static final String TAG = "CalendarActivity";

    /**
     * Data binding
     */
    private ActivityCalendarBinding activityCalendarBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCalendarBinding = DataBindingUtil.setContentView(this, R.layout.activity_calendar);
        CalendarBaseView calendarView = activityCalendarBinding.rlCalendarBaseView;
        calendarView.init(getApplicationContext());
    }
}