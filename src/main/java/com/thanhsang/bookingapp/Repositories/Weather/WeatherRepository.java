package com.thanhsang.bookingapp.Repositories.Weather;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhsang.bookingapp.Models.Weather.WeatherData;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherData, Long>{
    
}
