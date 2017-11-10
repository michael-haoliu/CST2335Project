package com.example.hliu.cst2335_project;

import com.example.hliu.cst2335_project.homeThermostatPkg.DTO.DTO_TemperatureSetting;

import org.junit.Test;

import static org.junit.Assert.*;

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
        assertEquals(4, 2 + 2);
    }
}