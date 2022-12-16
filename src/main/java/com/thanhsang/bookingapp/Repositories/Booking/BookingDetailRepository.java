package com.thanhsang.bookingapp.Repositories.Booking;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thanhsang.bookingapp.Models.Booking.BookingDetailKey;
import com.thanhsang.bookingapp.Models.Booking.BookingDetailModel;

@Repository
public interface BookingDetailRepository extends JpaRepository<BookingDetailModel, BookingDetailKey>{
    
    @Query(value = "DELETE FROM booking_detail WHERE booking_id = ?1", nativeQuery =  true)
    public void deleteByIdBooking(Long booking_id) throws Exception;

    @Query(value = "SELECT * FROM booking_detail WHERE booking_id = ?1", nativeQuery = true)
    public List<BookingDetailModel> findAllByIdBooking(Long book_id) throws Exception;
}
