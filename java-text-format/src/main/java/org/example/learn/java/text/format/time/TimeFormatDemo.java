package org.example.learn.java.text.format.time;

import java.util.Calendar;
import java.util.Date;

public class TimeFormatDemo {

    public void demoDefaultFormat() {
        Date now = Calendar.getInstance().getTime();
        System.out.println("now = " + now);
    }
}
