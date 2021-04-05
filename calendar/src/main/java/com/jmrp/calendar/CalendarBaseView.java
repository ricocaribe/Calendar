package com.jmrp.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.Calendar;
import java.util.Locale;

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
     * The current month
     */
    private int mCurrentPosition = 1;
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
        setUpViewPager();
    }

    /**
     * Sets up view pager
     */
    private void setUpViewPager() {

        mCalendarPagerAdapter = new CalendarPagerAdapter(((AppCompatActivity) getContext()).getSupportFragmentManager());
        mCalendarPagerAdapter.addView(CalendarItemView.newInstance(mCalendar), 0);
        mCalendarPagerAdapter.addView(CalendarItemView.newInstance(mCalendar), 1);
        mCalendarPagerAdapter.addView(CalendarItemView.newInstance(mCalendar), 2);

        //Pager animation for alpha effect
        viewPagerCalendar.setPageTransformer(false, (page, position) -> {
            position = (float) Math.sqrt(1 - Math.abs(position));
            page.setAlpha(position);
        });

        viewPagerCalendar.setAdapter(mCalendarPagerAdapter);

        //Selects middle item
        viewPagerCalendar.setCurrentItem(1);

        viewPagerCalendar.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG, "onPageScrolled: " + position);
            }

            @Override
            public void onPageSelected(int position) {


                Log.d(TAG, "onPageSelected: " + position);
                //init month with month days
                if (position==0) {

                    mCalendarPagerAdapter.addView(CalendarItemView.newInstance(mCalendar), 0);
                    setCalendarHeader();


                    Log.d(TAG, "setUpViewPager: previous month: " + mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, getResources().getConfiguration().locale));
                } else {

                    if (position == mCalendarPagerAdapter.getCount() - 1) {
                        mCalendarPagerAdapter.addView(CalendarItemView.newInstance(mCalendar), position + 1);

                        setCalendarHeader();

                    }


                    Log.d(TAG, "setUpViewPager: next month: " + mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, getResources().getConfiguration().locale));
                }
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
    private void setCalendarHeader() {
        tvCalendarMonth.setText(mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, getResources().getConfiguration().locale));
        tvCalendarYear.setText(String.valueOf(mCalendar.get(Calendar.YEAR)));
    }
}
