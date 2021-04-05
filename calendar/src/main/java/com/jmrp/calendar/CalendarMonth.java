package com.jmrp.calendar;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * An imputable representation of a day on a calendar, based on {@link Date}.
 */
public final class CalendarMonth implements Parcelable {

    /**
     * Everything is based on this variable for {@link CalendarMonth}.
     */
    @NonNull
    private ArrayList<CalendarDay> mCalendarDays = new ArrayList<>();
    private Date date;
    private static Calendar calendar;

    /**
     * @param year new instance's year
     * @param month new instance's month as defined by {@linkplain Calendar}
     * @param day new instance's day of month
     */
    private CalendarMonth(final int year, final int month, final int day) {
        calendar = Calendar.getInstance();
        calendar.set(year,month,day);
        date = calendar.getTime();
    }

    /**
     * Gets month date
     * @return the month date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date {@link Date} instance
     */
    private CalendarMonth(@NonNull final Date date) {
        this.date = date;
    }

    protected CalendarMonth(Parcel in) {
        mCalendarDays = in.createTypedArrayList(CalendarDay.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mCalendarDays);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CalendarMonth> CREATOR = new Creator<CalendarMonth>() {
        @Override
        public CalendarMonth createFromParcel(Parcel in) {
            return new CalendarMonth(in);
        }

        @Override
        public CalendarMonth[] newArray(int size) {
            return new CalendarMonth[size];
        }
    };
}
