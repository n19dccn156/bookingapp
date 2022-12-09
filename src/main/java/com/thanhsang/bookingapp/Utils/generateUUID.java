package com.thanhsang.bookingapp.Utils;

import java.util.UUID;

public class generateUUID {
    
    public static String generateUuid() {

        UUID id = UUID.randomUUID();
        return id.toString();
    }
}
