package com.thanhsang.bookingapp.Interfaces.Booking;

import org.springframework.http.ResponseEntity;

import com.thanhsang.bookingapp.Models.Addons.BookingRequest;
import com.thanhsang.bookingapp.Models.Addons.ResponseObject;

public interface BookingInterface {
    
    public ResponseEntity<ResponseObject> insert(BookingRequest bookingRequest, String access_token) throws Exception;
    public ResponseEntity<ResponseObject> updateState(Long booking_id, String state_id, String access_token, String ipClient) throws Exception;
    public ResponseEntity<ResponseObject> cancelBooking(Long booking_id, String access_token) throws Exception;
    public ResponseEntity<ResponseObject> getBooking(Long booking_id) throws Exception;

}
