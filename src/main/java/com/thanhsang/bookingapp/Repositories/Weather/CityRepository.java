package com.thanhsang.bookingapp.Repositories.Weather;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhsang.bookingapp.Models.Weather.CityModel;

@Repository
public interface CityRepository extends JpaRepository<CityModel, String>{
    
}
