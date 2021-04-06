package com.jmrp.calendar;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Calendar;

public class CalendarBaseView extends RelativeLayout {

    /**
     * The TAG constant
     */
    public static final String TAG = "CalendarView";
    /**
     * Current calendar instance
     */
    private final Calendar mCalendar = Calendar.getInstance();
    /**
     * The calendar view pager adapter
     */
    private CalendarPagerAdapter mCalendarPagerAdapter;
    /**
     * the calendar view pager
     */
    private CalendarViewPager viewPagerCalendar;
    /**
     * The root view
     */
    private View rootView;
    /**
     * The calendar month view
     */
    private TextView tvCalendarMonth;
    /**
     * The calendar year view
     */
    private TextView tvCalendarYear;
    /**
     * The current month
     */
    private int mCurrentMonth = Calendar.getInstance().get(Calendar.MONTH);
    /**
     * The current year
     */
    private int mCurrentYear = Calendar.getInstance().get(Calendar.YEAR);
    /**
     * The current pager position
     */
    private int mCuerrentPosition;

    /**
     * The calendar constructor
     *
     * @param context the context
     */
    public CalendarBaseView(Context context) {
        super(context);
        init(context);
    }

    /**
     * The calendar constructor
     *
     * @param context the context
     * @param attrs   the attributes
     */
    public CalendarBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * The calendar constructor
     *
     * @param context      the context
     * @param attrs        the attributes
     * @param defStyleAttr the def styles
     */
    public CalendarBaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * The calendar constructor
     *
     * @param context      the context
     * @param attrs        the attributes
     * @param defStyleAttr the def styles
     * @param defStyleRes  the def styles res
     */
    public CalendarBaseView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * Init  calendar
     *
     * @param context the context
     */
    public void init(Context context) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        rootView = mInflater.inflate(R.layout.calendar_layout, this, true);
        viewPagerCalendar = rootView.findViewById(R.id.vpCalendar);

        initViews();
        setUpViewPager(context);
    }

    /**
     * Sets up view pager
     */
    private void setUpViewPager(Context context) {

        ArrayList<View> mCalendarItemViews = new ArrayList<>();

        mCalendarItemViews.add(new CalendarItemView(getContext()));
        //mCalendarItemViews.add(new CalendarItemView(getContext()));
        //mCalendarItemViews.add(new CalendarItemView(getContext()));

        mCalendarPagerAdapter = new CalendarPagerAdapter(mCalendarItemViews, mCalendar);

        //Pager animation for alpha effect
        viewPagerCalendar.setPageTransformer(false, (page, position) -> {
            position = (float) Math.sqrt(1 - Math.abs(position));
            page.setAlpha(position);
        });

        viewPagerCalendar.setAdapter(mCalendarPagerAdapter);

        //Selects middle item
        viewPagerCalendar.setCurrentItem(0);

        viewPagerCalendar.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG, "onPageScrolled: " + position);
            }

            @Override
            public void onPageSelected(int position) {


                Log.d(TAG, "onPageSelected: " + position);
                //init month with month days
                if (position == 0) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            updateCalendar(false);
                            CalendarItemView calendarItemView = new CalendarItemView(getContext());
                            mCalendarPagerAdapter.addView(calendarItemView, 0);
                        }
                    }, 100);


                } else if (position == mCalendarPagerAdapter.getCount() - 1) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            updateCalendar(true);
                            CalendarItemView calendarItemView = new CalendarItemView(getContext());
                            mCalendarPagerAdapter.addView(calendarItemView, position+1);

                        }
                    }, 100);
                } else updateCalendar(mCuerrentPosition < position);

                mCuerrentPosition = position;

                Log.d(TAG, "setUpViewPager: month: " + mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, getResources().getConfiguration().locale));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(TAG, "onPageScrollStateChanged: " + state);
            }
        });
    }

    /**
     * Init calendar header, month and year
     */
    private void initViews() {
        tvCalendarMonth = rootView.findViewById(R.id.tvCalendarMonth);
        tvCalendarMonth.setText(mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, getResources().getConfiguration().locale));

        tvCalendarYear = rootView.findViewById(R.id.tvCalendarYear);
        tvCalendarYear.setText(String.valueOf(mCalendar.get(Calendar.YEAR)));
    }

    /**
     * Update calendar header, month and year
     */
    private void updateCalendar(boolean increase) {

        Log.d(TAG, "updateCalendar: ");
        Log.d(TAG, "updateCalendar: Current Month -> " + mCurrentMonth);
        Log.d(TAG, "updateCalendar: Current Year -> " + mCurrentYear);

        if (increase) {
            if (mCurrentMonth == 11) {
                mCurrentMonth = 0;
                mCalendar.set(Calendar.MONTH, mCurrentMonth);

                mCalendar.set(Calendar.YEAR, mCalendar.get(Calendar.YEAR) + 1);
                mCurrentYear = mCalendar.get(Calendar.YEAR);
            } else {
                mCalendar.set(Calendar.MONTH, mCalendar.get(Calendar.MONTH) + 1);
                mCurrentMonth = mCalendar.get(Calendar.MONTH);
            }
        } else {
            if (mCurrentMonth == 0) {

                mCurrentMonth = 11;
                mCalendar.set(Calendar.MONTH, mCurrentMonth);

                mCalendar.set(Calendar.YEAR, mCalendar.get(Calendar.YEAR) - 1);
                mCurrentYear = mCalendar.get(Calendar.YEAR);
            } else {
                mCalendar.set(Calendar.MONTH, mCalendar.get(Calendar.MONTH) - 1);
                mCurrentMonth = mCalendar.get(Calendar.MONTH);
            }
        }

        Log.d(TAG, "updateCalendar: Current Month updated -> " + mCurrentMonth);
        Log.d(TAG, "updateCalendar: Current Year updated -> " + mCurrentYear);
/*
        mCalendar.set(Calendar.MONTH, mCurrentMonth);
        mCalendar.set(Calendar.YEAR, mCurrentYear);*/

        tvCalendarMonth.setText(mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, getResources().getConfiguration().locale));
        tvCalendarYear.setText(String.valueOf(mCalendar.get(Calendar.YEAR)));

        /*tvCalendarMonth.setText(mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, getResources().getConfiguration().locale));
        tvCalendarYear.setText(String.valueOf(mCalendar.get(Calendar.YEAR)));*/
    }
}
