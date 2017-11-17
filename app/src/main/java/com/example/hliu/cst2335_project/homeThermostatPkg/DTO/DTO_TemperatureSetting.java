package com.example.hliu.cst2335_project.homeThermostatPkg.DTO;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by H.LIU on 2017-11-08.
 */

public class DTO_TemperatureSetting extends AppCompatActivity implements Comparable, Serializable {

    private String dayOfWeek;
    private String timeOfDay;

    private int minOfWeek;
    private double temp;

    private static final int MIN_PER_DAY = 1440; //60min * 24 hrs


//    private String Mon = getResources().getString(R.string.MONDAY);
//    private final String[] dayofWeek = getResources().getStringArray(R.array.stringArray_dayOfWeek_h);

    public DTO_TemperatureSetting() {
    }

    public DTO_TemperatureSetting(int minOfWeek, double temp) {
        this.minOfWeek = minOfWeek;
        this.temp = temp;
    }

    public DTO_TemperatureSetting(String day, int hour, int min, double temp) {
        String Day_Hour_Min = String.format("%s %02d:%02d", day,hour,min);

        minOfWeek = parseMinOfWeek(Day_Hour_Min, Locale.getDefault());
        this.temp = temp;
//        timeOfWeek = parseMinOfWeek(Day_Hour_Min, Resources.getSystem().getConfiguration().getLocales().get(0));
//        timeOfWeek = parseMinOfWeek(Day_Hour_Min, Resources.getSystem().getConfiguration().locale);
//        int totalMin = converDayToMin(day) + hour * 60 + min;
//        this.timeOfWeek = totalMin;
//        new DTO_TemperatureSetting(totalMin, temp);
//        System.out.println("new constructor with string and number input: " + this.toString());
    }

    public DTO_TemperatureSetting(String day_time_temp) {
        String[] str = day_time_temp.split(" ");
        String day = str[0].trim();
        String time_str = str[1].trim();
        String temp_str = str[(str.length - 1)].trim();

        time_str = day + " " + time_str;
        System.out.println("string read time is " + time_str + " "+temp_str);

        minOfWeek = parseMinOfWeek((time_str), Locale.getDefault());
        try {
            Double temp = Double.parseDouble(temp_str);
            this.temp = temp;
        } catch (Exception e) {
            this.temp = -900;
            System.err.println("new constructor bad temperature string input: " + temp_str);
        }
        System.out.println("string read time is " + toString() + "  *");

/*        String[] timeString = time_str.split(":");
        String hour_str = timeString[0].trim();
        String min_str = timeString[1].trim();

        try {
            int hour = Integer.parseInt(hour_str);
            int min = Integer.parseInt(min_str);
            int totalMin = converDayToMin(day) + hour * 60 + min;

            this.minOfWeek = totalMin;

        } catch (Exception e) {
            System.err.println("new constructor bad time string input: " + timeString);
        }*/


    }



    //---------------------------

    // can not set 2 setting at the same time
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DTO_TemperatureSetting) {
            return (minOfWeek == ((DTO_TemperatureSetting) obj).getTimeOfWeek());
        }
        return false;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        if (getTimeOfWeek() == minOfWeek)
            return 0;
        else if (getTimeOfWeek() > minOfWeek)
            return -1;
        else {
            return 1;
        }
    }

    @Override
    public int hashCode() {
        return getTimeOfWeek();
    }

    @Override
    public String toString() {
        String temp = " Temp -> ";
        if(Locale.getDefault().getLanguage().equals("zh")){
            temp = " 温度 -> ";
        }
        return displayTime(Locale.getDefault())
                + temp
                + getTemp();
//        return getDayOfWeek() + " "
//                + getTimeOfDay() + " "
//                + "temp -> " + getTemp();
    }

    public String displayTime(Locale locale) {
        String day;


        int dayOfWeek = (minOfWeek / MIN_PER_DAY) ;
        int timeOfDay = minOfWeek % MIN_PER_DAY;
        int hour = timeOfDay / 60;
        int min = timeOfDay % 60;

        switch (dayOfWeek) {
            case 1:
                day = "SUNDAY";
                break;
            case 2:
                day = "MONDAY";
                break;
            case 3:
                day = "TUESDAY";
                break;
            case 4:
                day = "WEDNESDAY";
                break;
            case 5:
                day = "THURSDAY";
                break;
            case 6:
                day = "FRIDAY";
                break;
            case 7:
                day = "SATURDAY";
                break;
            default:
                day = " something is wrong week of day: " + dayOfWeek;
        }

        String day_Hour_Min = String.format(locale,"%s %02d:%02d", day,hour,min);

        System.out.println(" \n display time is " +  day_Hour_Min);
        return day_Hour_Min;

    }

    public int parseMinOfWeek(String Day_Hour_Min, Locale locale) {
        try {
            SimpleDateFormat dayFormat = new SimpleDateFormat("E HH:mm", locale);
            Date date = dayFormat.parse(Day_Hour_Min);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            int totalMin = calendar.get(Calendar.MINUTE) + calendar.get(Calendar.HOUR_OF_DAY) * 60 + (calendar.get(Calendar.DAY_OF_WEEK) ) * MIN_PER_DAY;
            System.out.println("parse min of week read: " + calendar.get(Calendar.DAY_OF_WEEK) + " "
                    + calendar.get(Calendar.HOUR_OF_DAY)
                    + " " + calendar.get(Calendar.MINUTE));

            return totalMin;
        } catch (Exception e) {

            Log.e("DTO", "error parseMinofWeek: " + e.toString());
            return -999;
        }
    }

    public String getStringEEEE_HH_mm(int minOfweek, Locale locale) {
        setTimeOfWeek(minOfweek);
        String str_time = displayTime(locale);
//        System.out.println("test data: " + str_time  );
        try {
            SimpleDateFormat dayFormat = new SimpleDateFormat("E HH:mm", locale);
            Date date = dayFormat.parse(str_time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            dayFormat = new SimpleDateFormat("EEEE HH:mm", locale);
            String str = dayFormat.format(calendar.getTime());
            System.out.println("test data: " + str);
            return str;
        } catch (Exception e) {
            return "exception of get string day of week by min of the week";
        }

    }
    //-----------------------------
    public int getTimeOfWeek() {
        return minOfWeek;
    }

    public void setTimeOfWeek(int timeOfWeek) {
        this.minOfWeek = timeOfWeek;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }


    //--------------------------------
    /*
     not used this method
     */
    public int parseDayOfWeek(String day, Locale locale)
            throws ParseException {

        SimpleDateFormat dayFormat = new SimpleDateFormat("E HH:mm", locale);
        Date date = dayFormat.parse(day);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        dayFormat = new SimpleDateFormat("EEEE", locale);
        System.out.println("test data: " + dayFormat.format(date));

        dayFormat = new SimpleDateFormat("EEEE HH:mm", locale);
        System.out.println("test data: " + dayFormat.format(date));

        int minOfweek = calendar.get(Calendar.MINUTE);
        System.out.println("test data: " + minOfweek);


        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek;
    }

    //------------------

/*    public String getTimeOfDay() {
        int timeOfDay = minOfWeek % MIN_PER_DAY;
        int hour = timeOfDay / 60;
        int min = timeOfDay % 60;

//        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
//        System.out.println(String.format("%02d:%02d", hour, min));

        return String.format("%02d:%02d", hour, min);
//        return String.format("%H:%M", hour, min);
    }*/

/*    private int converDayToMin(String dayOfWeek) {
        dayOfWeek = dayOfWeek.toUpperCase();
        switch (dayOfWeek) {
            case "SUNDAY":
                return 0;
            case "MONDAY":
                return 1 * MIN_PER_DAY;
            case "TUESDAY":
                return 2 * MIN_PER_DAY;
            case "WEDNESDAY":
                return 3 * MIN_PER_DAY;
            case "THURSDAY":
                return 4 * MIN_PER_DAY;
            case "FRIDAY":
                return 5 * MIN_PER_DAY;
            case "SATURDAY":
                return 6 * MIN_PER_DAY;
        }
        Log.i("DTO", "conver day of week to minutes is wrong");
        return -1;
    }*/

//    public String getDayOfWeek() {
//        int dayOfWeek = timeOfWeek / MIN_PER_DAY;
//        switch (dayOfWeek) {
//            case 1:
//                return "SUNDAY";
//            case 2:
////                return getResources().getString(R.string.MONDAY);
//                return "MONDAY";
//            case 3:
//                return "TUESDAY";
//            case 4:
//                return "WEDNESDAY";
//            case 5:
//                return "THURSDAY";
//            case 6:
//                return "FRIDAY";
//            case 7:
//                return "SATURDAY";
//
//        }
//        return "something is wrong week of day";
//    }
}
