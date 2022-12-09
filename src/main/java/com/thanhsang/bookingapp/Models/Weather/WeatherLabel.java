package com.thanhsang.bookingapp.Models.Weather;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "weather_label")
@Data
@AllArgsConstructor
public class WeatherLabel {
    
    @Id
    private Integer id;

    @Column(name = "name", nullable = false, unique = true, length = 20)
    private String name;
}
