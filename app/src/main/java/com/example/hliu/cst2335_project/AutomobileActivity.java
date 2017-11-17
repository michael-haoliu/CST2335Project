package com.example.hliu.cst2335_project;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TimePicker;

import com.example.hliu.cst2335_project.homeThermostatPkg.DTO.DTO_TemperatureSetting;

public class AutomobileActivity extends Activity {

    private TimePicker timePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automobile);

        timePicker = findViewById(R.id.timePicker1_h);
        timePicker.setIs24HourView(true);

        int min = timePicker.getCurrentMinute();
        int hour = timePicker.getCurrentHour();

        DTO_TemperatureSetting obj = new DTO_TemperatureSetting("Monday", hour, min, 10);

        Log.i("test" , " test time pick  " + obj.toString());
    }


}
