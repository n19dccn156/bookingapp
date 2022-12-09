package com.thanhsang.bookingapp.Repositories.Hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhsang.bookingapp.Models.Hotel.ConvenientModel;

@Repository
public interface ConvenientRepository extends JpaRepository<ConvenientModel, Long>{
    
}
