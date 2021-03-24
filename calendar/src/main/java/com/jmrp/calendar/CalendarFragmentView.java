package com.jmrp.calendar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.jmrp.calendar.databinding.ItemMonthViewBinding;

import java.util.Random;

public class CalendarFragmentView extends Fragment {

    private int number;

    public static CalendarFragmentView newInstance(int number) {
        CalendarFragmentView calendarFragmentView = new CalendarFragmentView();
        Bundle args = new Bundle();
        args.putInt("number", number);
        calendarFragmentView.setArguments(args);
        return calendarFragmentView;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        number = getArguments().getInt("number", 0);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ItemMonthViewBinding monthViewBinding = DataBindingUtil.inflate(inflater, R.layout.item_month_view, container, false);
        monthViewBinding.tvTest.setText(String.valueOf(number));
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        monthViewBinding.rlContainerMonth.setBackgroundColor(color);
        return monthViewBinding.getRoot();
    }
}
