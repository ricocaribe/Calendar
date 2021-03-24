package com.jmrp.calendar;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

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

        initCalendar();
    }

    private void initCalendar() {
        FragmentPagerAdapter calendarPagerAdapter = new CalendarPagerAdapter(getSupportFragmentManager());
        activityCalendarBinding.vpCalendar.setAdapter(calendarPagerAdapter);

        activityCalendarBinding.vpCalendar.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG, "onPageScrolled: ");
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected: ");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(TAG, "onPageScrollStateChanged: ");
            }
        });
    }
}