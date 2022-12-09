package com.thanhsang.bookingapp.Utils;

import java.util.Random;

public class GenerateId {
    
    public static Long generateId() {
        Random rd = new Random();   // khai báo 1 đối tượng Random
        long result = Math.abs(rd.nextLong());
        return Long.valueOf(result);
    }
}
