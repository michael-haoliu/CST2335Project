package com.example.hliu.cst2335_project.homeThermostatPkg.DTO;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.Serializable;

/**
 * Created by H.LIU on 2017-11-08.
 */

public class DTO_TemperatureSetting extends AppCompatActivity implements Comparable, Serializable{

    private String dayOfWeek;
    private String timeOfDay;

    private int timeOfWeek;
    private double temp;

    private static final int MIN_PER_DAY = 1440; //60min * 24 hrs



//    private String Mon = getResources().getString(R.string.MONDAY);
//    private final String[] dayofWeek = getResources().getStringArray(R.array.stringArray_dayOfWeek_h);

    public DTO_TemperatureSetting() {
    }

    public DTO_TemperatureSetting(int timeOfWeek, double temp) {
        this.timeOfWeek = timeOfWeek;
        this.temp = temp;
    }

    public DTO_TemperatureSetting(String day, int hour, int min, double temp) {
        int totalMin = converDayToMin(day) + hour * 60 + min;
        this.timeOfWeek = totalMin;
        this.temp = temp;
//        new DTO_TemperatureSetting(totalMin, temp);
        System.out.println("new constructor with string and number input: " + this.toString());
    }
    public DTO_TemperatureSetting(String day_time_temp) {
        String[] str = day_time_temp.split(" ");
        String day = str[0].trim();
        String time_str= str[1].trim();
        String temp_str = str[ (str.length-1) ].trim();

        String[] timeString = time_str.split(":");
        String hour_str = timeString[0].trim();
        String min_str  = timeString[1].trim();

        try {
            int hour = Integer.parseInt(hour_str);
            int min = Integer.parseInt(min_str);
            int totalMin = converDayToMin(day) + hour * 60 + min;

            this.timeOfWeek = totalMin;

        }catch (Exception e){
            System.err.println("new constructor bad time string input: " + timeString );
        }
        try {
            Double temp = Double.parseDouble(temp_str);
            this.temp = temp;
        }catch (Exception e){
            this.temp = -900;
            System.err.println("new constructor bad temperature string input: " + temp_str );
        }
        System.out.println("new constructor with String input" + this.toString());
    }

    //------------------
    private int converDayToMin(String dayOfWeek){
        dayOfWeek = dayOfWeek.toUpperCase();
        switch (dayOfWeek){
            case "MONDAY":
                return 0;
            case "TUESDAY":
                return 1 * MIN_PER_DAY;
            case "WEDNESDAY":
                return 2 * MIN_PER_DAY;
            case "THURSDAY":
                return 3 * MIN_PER_DAY;

            case "FRIDAY":
                return 4 * MIN_PER_DAY;
            case "SATURDAY":
                return 5 * MIN_PER_DAY;
            case "SUNDAY":
                return 6 * MIN_PER_DAY;
        }
        Log.i("DTO", "conver day of week to minutes is wrong");
        return -1;
    }

    public String getDayOfWeek() {
        int dayOfWeek = timeOfWeek/MIN_PER_DAY;
        switch (dayOfWeek){
            case 0:
//                return getResources().getString(R.string.MONDAY);
                return "MONDAY";
            case 1:
                return "TUESDAY";
            case 2:
                return "WEDNESDAY";
            case 3:
                return "THURSDAY";
            case 4:
                return "FRIDAY";
            case 5:
                return "SATURDAY";
            case 6:
                return "SUNDAY";
        }
        return "something is wrong week of day";
    }

    public String getTimeOfDay() {
        int timeOfDay = timeOfWeek % MIN_PER_DAY;
        int hour = timeOfDay/60;
        int min = timeOfDay % 60;

//        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        return String.format("%02d:%02d", hour, min);
//        return String.format("%H:%M", hour, min);
    }

    //---------------------------

    // can not set 2 setting at the same time
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof DTO_TemperatureSetting){
            return (timeOfWeek == ((DTO_TemperatureSetting) obj).getTimeOfWeek());
        }
        return false;
    }
    @Override
    public int compareTo(@NonNull Object o) {
        if(getTimeOfWeek() == timeOfWeek)
            return 0;
        else if(getTimeOfWeek() > timeOfWeek)
            return -1;
        else{
            return 1;
        }
    }

    @Override
    public int hashCode() {
        return getTimeOfWeek();
    }

    @Override
    public String toString() {
        return getDayOfWeek() + " "
                + getTimeOfDay() + " "
                + "temp -> " + getTemp();
    }

    public String displayTime() {
        return getDayOfWeek() + " "
                + getTimeOfDay();
    }

    //-----------------------------
    public int getTimeOfWeek() {
        return timeOfWeek;
    }
    public void setTimeOfWeek(int timeOfWeek) {
        this.timeOfWeek = timeOfWeek;
    }

    public double getTemp() {
        return temp;
    }
    public void setTemp(double temp) {
        this.temp = temp;
    }

}
