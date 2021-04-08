package com.jmrp.calendar;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Calendar;

import static com.jmrp.calendar.CalendarItemView.CALENDAR_KEY;

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
    private int mCurrentPosition;

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

        mCalendarPagerAdapter = new CalendarPagerAdapter(((AppCompatActivity) getContext()).getSupportFragmentManager(), mCalendar);

        //Pager animation for alpha effect
        viewPagerCalendar.setPageTransformer(false, (page, position) -> {
            position = (float) Math.sqrt(1 - Math.abs(position));
            page.setAlpha(position);
        });

        viewPagerCalendar.setAdapter(mCalendarPagerAdapter);

        //Selects middle item
        mCurrentPosition = Integer.MAX_VALUE/2;
        viewPagerCalendar.setCurrentItem(mCurrentPosition);

        viewPagerCalendar.addOnPageChangeListener(mOnPageChangeListener);

        //mOnPageChangeListener.onPageSelected(viewPagerCalendar.getCurrentItem());
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
    private void updateCalendar(Calendar calendar) {

        Log.d(TAG, "updateCalendar: month: " + calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, getResources().getConfiguration().locale));


        /*if (increase) {
            if (mCurrentMonth == 11) {
                mCurrentMonth = 0;
                mCalendar.set(Calendar.MONTH, mCurrentMonth);

                mCalendar.set(Calendar.YEAR, mCalendar.get(Calendar.YEAR) + 1);
                mCurrentYear = mCalendar.get(Calendar.YEAR);
            } else {
                mCalendar.set(Calendar.MONTH, mCurrentMonth + 1);
                mCurrentMonth = mCalendar.get(Calendar.MONTH);
            }
        } else {
            if (mCurrentMonth == 0) {

                mCurrentMonth = 11;
                mCalendar.set(Calendar.MONTH, mCurrentMonth);

                mCalendar.set(Calendar.YEAR, mCalendar.get(Calendar.YEAR) - 1);
                mCurrentYear = mCalendar.get(Calendar.YEAR);
            } else {
                mCalendar.set(Calendar.MONTH, mCurrentMonth - 1);
                mCurrentMonth = mCalendar.get(Calendar.MONTH);
            }
        }

        Log.d(TAG, "updateCalendar: Current Month updated -> " + mCurrentMonth);
        Log.d(TAG, "updateCalendar: Current Year updated -> " + mCurrentYear);*/

        tvCalendarMonth.setText(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, getResources().getConfiguration().locale));
        tvCalendarYear.setText(String.valueOf(calendar.get(Calendar.YEAR)));
    }

    private final ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Log.d(TAG, "onPageScrolled: " + position);
        }

        @Override
        public void onPageSelected(int position) {

            /*if(mCurrentPosition<position){
                //Show next month
                updateCalendar(true);
            }
            else {
                //Show previous month
                updateCalendar(false);
            }

            mCurrentPosition = position;*/

            //updateCalendar((Calendar)(mCalendarPagerAdapter.getItem(position)).getArguments().get(CALENDAR_KEY));
            updateCalendar((Calendar)(mCalendarPagerAdapter.getRegisteredFragment(position)).getArguments().get(CALENDAR_KEY));
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            Log.d(TAG, "onPageScrollStateChanged: " + state);
        }
    };
}
