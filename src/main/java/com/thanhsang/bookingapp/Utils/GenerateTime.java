package com.thanhsang.bookingapp.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class GenerateTime {
    
    public static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public static String getCurrentTimeStamp() {
        // System.out.println("timeStamp" + dtf.format(LocalDateTime.now()));
        return dtf.format(LocalDateTime.now());
    }

    public static Long getCurrentTimeStampSecond() {
        Date date = new Date();
        long msec = date.getTime();
        return msec;
    }

    public static Long getCurrentTimeStampSecond1Hour() {
        Date date = new Date();
        long tenMinute = 1000 * 60 * 60;
        long msec = date.getTime() + tenMinute;
        return msec;
    }

    public static Long getCurrentTimeStampSecond7Day() {
        Date date = new Date();
        long sevenDay = 1000 * 60 * 60 * 24 * 7;
        long msec = date.getTime() + sevenDay;
        return msec;
    }
}
