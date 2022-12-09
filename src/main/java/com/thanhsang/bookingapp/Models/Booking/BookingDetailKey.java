package com.thanhsang.bookingapp.Models.Booking;

import java.io.Serializable;

import lombok.Data;

@Data
public class BookingDetailKey implements Serializable {
    
    private Long booking_id;
    private Long room_id;
}
