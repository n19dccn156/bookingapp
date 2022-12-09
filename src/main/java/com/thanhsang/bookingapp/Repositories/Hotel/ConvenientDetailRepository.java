package com.thanhsang.bookingapp.Repositories.Hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhsang.bookingapp.Models.Hotel.ConvenientDetailModel;
import com.thanhsang.bookingapp.Models.Hotel.ConvenientKey;

@Repository
public interface ConvenientDetailRepository extends JpaRepository<ConvenientDetailModel, ConvenientKey>{
    
}
