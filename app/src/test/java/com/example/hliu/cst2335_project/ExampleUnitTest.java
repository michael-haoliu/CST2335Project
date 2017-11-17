package com.example.hliu.cst2335_project;

import com.example.hliu.cst2335_project.homeThermostatPkg.DTO.DTO_TemperatureSetting;

import org.junit.Test;

import java.text.SimpleDateFormat;
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

        DTO_TemperatureSetting tempObj2 = new DTO_TemperatureSetting("Monday 02:11 Temp -> 20");
        System.out.println("added temp rule is " + tempObj2.toString());

        Locale.setDefault(Locale.CHINESE);
        DTO_TemperatureSetting tempObj1 = new DTO_TemperatureSetting("星期日", 2, 10, 20);

        System.out.println("added temp rule is " + tempObj1.toString());
        System.out.println("--------------------------");

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


            int dayOfWeek = tempObj.parseDayOfWeek("Sunday 01:10", Locale.US);
            System.out.println(dayOfWeek);



            dayOfWeek = tempObj.parseDayOfWeek("Tue 01:10", Locale.US);
            System.out.println(dayOfWeek);

            dayOfWeek = tempObj.parseDayOfWeek("Sonntag 01:10", Locale.GERMANY);
            System.out.println(dayOfWeek);



        dayOfWeek = tempObj.parseDayOfWeek("星期日 01:10", Locale.CHINESE);
        System.out.println(dayOfWeek);

        System.out.println("-------------");
        int minOfWeek = tempObj.parseMinOfWeek("Sunday 01:10", Locale.US);
        System.out.println(minOfWeek);
        String string = tempObj.getStringEEEE_HH_mm(minOfWeek, Locale.US);

        minOfWeek = tempObj.parseMinOfWeek("SATURDAY 00:01", Locale.US);
        System.out.println(minOfWeek);
        string = tempObj.getStringEEEE_HH_mm(minOfWeek, Locale.US);

        minOfWeek = tempObj.parseMinOfWeek("SATURDAY 23:59", Locale.US);
        System.out.println(minOfWeek);
        string = tempObj.getStringEEEE_HH_mm(minOfWeek, Locale.US);

        minOfWeek = tempObj.parseMinOfWeek("SATURDAY 11:59", Locale.US);
        System.out.println(minOfWeek);
        string = tempObj.getStringEEEE_HH_mm(minOfWeek, Locale.US);

        assertEquals(4, 2 + 2);
    }




}