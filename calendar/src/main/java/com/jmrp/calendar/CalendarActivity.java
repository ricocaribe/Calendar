package com.jmrp.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class CalendarActivity extends AppCompatActivity {

    /**
     * The constant TAG
     */
    public static final String TAG = "CalendarActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Toast.makeText(getApplicationContext(), "onCreate", Toast.LENGTH_LONG).show();
    }
}