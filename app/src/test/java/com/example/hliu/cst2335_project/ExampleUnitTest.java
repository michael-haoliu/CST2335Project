package com.example.hliu.cst2335_project;

import com.example.hliu.cst2335_project.homeThermostatPkg.DTO.DTO_TemperatureSetting;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testclass1() throws Exception {
        DTO_TemperatureSetting tempObj = new DTO_TemperatureSetting("Monday", 2, 10, 20);

        System.out.println("added temp rule is " + tempObj.toString());

//        String sunday = "Sunday";
////        System.out.println("test data: " + DayOfWeek.valueOf(sunday));
//
////        sunday = "星期日";
//
//        sunday = "Sunday";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
//        Date date = simpleDateFormat.parse(sunday);
//        System.out.println("test data: " + date.toString());
//
//        Calendar calendar = Calendar.getInstance();


            int dayOfWeek = tempObj.parseDayOfWeek("Sunday", Locale.US);
            System.out.println(dayOfWeek);

            dayOfWeek = tempObj.parseDayOfWeek("Tue", Locale.US);
            System.out.println(dayOfWeek);

            dayOfWeek = tempObj.parseDayOfWeek("Sonntag", Locale.GERMANY);
            System.out.println(dayOfWeek);



        dayOfWeek = tempObj.parseDayOfWeek("星期日", Locale.CHINESE);
        System.out.println(dayOfWeek);


        Date date = new Date(dayOfWeek);
        System.out.println("test data: " + date.toString());

//        System.out.println("test data: " + DayOfWeek.valueOf(sunday));


        assertEquals(4, 2 + 2);
    }




}