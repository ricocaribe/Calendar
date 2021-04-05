package com.jmrp.calendar;


import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Calendar;
import java.util.Date;

/**
 * An imputable representation of a day on a calendar, based on {@link Date}.
 */
public final class CalendarDay implements Parcelable {

    /**
     * The TAG constant
     */
    public static final String TAG = "CalendarDay";
    /**
     * CalendarDay current day
     */
    private final int mDay;
    /**
     * CalendarDay current month
     */
    private final int mMonth;
    /**
     * CalendarDay current year
     */
    private final int mYear;

    /**
     * @param year new instance's year
     * @param month new instance's month as defined by {@linkplain java.util.Calendar}
     * @param day new instance's day of month
     */
    private CalendarDay(final int year, final int month, final int day) {
        this.mDay = day;
        this.mMonth = month;
        this.mYear = year;
        Log.d(TAG, "CalendarDay: " + toString());
    }



    /**
     * Get a new instance set to the specified day
     *
     * @param year new instance's year
     * @param month new instance's month as defined by {@linkplain java.util.Calendar}
     * @param day new instance's day of month
     * @return CalendarDay set to the specified date
     */
    @NonNull public static CalendarDay from(int year, int month, int day) {
        return new CalendarDay(year, month, day);
    }

    /**
     * Get the year, represented by values from {@linkplain Calendar}
     *
     * @return the year for this day as defined by {@linkplain Calendar}
     */
    public int getYear() {
        return mYear;
    }

    /**
     * Get the month, represented by values from {@linkplain Calendar}
     *
     * @return the month of the year as defined by {@linkplain Calendar}
     */
    public int getMonth() {
        return mMonth;
    }

    /**
     * Get the day, represented by values from {@linkplain Calendar}
     *
     * @return the day of the year as defined by {@linkplain Calendar}
     */
    public int getDay() {
        return mDay;
    }

    /**
     * Determine if this day is within a specified range
     *
     * @param minDate the earliest day, may be null
     * @param maxDate the latest day, may be null
     * @return true if the between (inclusive) the min and max dates.
     */
    /*public boolean isInRange(@Nullable Date minDate, @Nullable Date maxDate) {
        return (mCalendar.getTime().before(maxDate)  && mCalendar.getTime().after(minDate));
    }*/

    @Override
    public String toString() {
        return "CalendarDay{" + mYear + "-" + mMonth + "-" + mDay + "}";
    }

    /**
     * Parcelable Stuff
     */
    public CalendarDay(Parcel in) {
        this(in.readInt(), in.readInt(), in.readInt());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mYear);
        dest.writeInt(mMonth);
        dest.writeInt(mDay);
    }

    public static final Creator<CalendarDay> CREATOR = new Creator<CalendarDay>() {
        public CalendarDay createFromParcel(Parcel in) {
            return new CalendarDay(in);
        }

        public CalendarDay[] newArray(int size) {
            return new CalendarDay[size];
        }
    };
}
