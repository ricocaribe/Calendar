package com.jmrp.customcalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.jmrp.calendar.CalendarActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Arrancamos Calendar Activity
        Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}