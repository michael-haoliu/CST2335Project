package com.example.hliu.cst2335_project.homeThermostatPkg.DTO;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by H.LIU on 2017-11-08.
 */

public class DTO_TemperatureSetting implements Comparable{

    private String dayOfWeek;
    private String timeOfDay;

    private int timeOfWeek;
    private double temp;

    private static final int MIN_PER_DAY = 1440; //60min * 24 hrs

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
        System.out.println("new constructor " + this.toString());
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
        return String.format("%d:%d", hour, min);
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
                + "temp ->" + getTemp();
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
