package com.thanhsang.bookingapp.Models.Weather;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "weather_data")
@Data
@AllArgsConstructor
public class WeatherData {
    
    @Id
    private Long id;

    @Column(name = "city_id", nullable = false, length = 20)
    private String city_id;

    @Column(name = "weather_actual", nullable = false)
    private Integer weather_actual;

    @Column(name = "date", nullable = false)
    private Date date;
    
    @Column(name = "temp_min", nullable = false)
    private Integer temp_min;
    
    @Column(name = "temp_max", nullable = false)
    private Integer temp_max;
    
    @Column(name = "wind_speed", nullable = false)
    private Integer wind_speed;
    
    @Column(name = "wind_dir", nullable = false)
    private Float wind_dir;
    
    @Column(name = "rain", nullable = false)
    private Float rain;
    
    @Column(name = "humidi", nullable = false)
    private Integer humidi;
    
    @Column(name = "cloud", nullable = false)
    private Integer cloud;
    
    @Column(name = "pressure", nullable = false)
    private Integer pressure;

}
