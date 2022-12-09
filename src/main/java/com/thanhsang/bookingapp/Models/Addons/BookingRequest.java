package com.thanhsang.bookingapp.Models.Addons;

import java.util.List;

import com.thanhsang.bookingapp.Models.Booking.BookingDetailModel;
import com.thanhsang.bookingapp.Models.Booking.BookingModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {
    
    private BookingModel booking;
    private List<BookingDetailModel> book_detail;
}
