package com.thanhsang.bookingapp.Repositories.Booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhsang.bookingapp.Models.Booking.RatingModel;

@Repository
public interface RatingRepository extends JpaRepository<RatingModel, Long>{
    
}
