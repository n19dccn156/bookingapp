package com.thanhsang.bookingapp.Repositories.Booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhsang.bookingapp.Models.Booking.StatusBookingModel;

@Repository
public interface StatusBookingRepository extends JpaRepository<StatusBookingModel, String>{
    
}
