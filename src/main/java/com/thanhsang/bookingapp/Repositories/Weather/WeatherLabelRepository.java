package com.thanhsang.bookingapp.Repositories.Weather;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhsang.bookingapp.Models.Weather.WeatherLabel;

@Repository
public interface WeatherLabelRepository extends JpaRepository<WeatherLabel, Integer>{
    
}
