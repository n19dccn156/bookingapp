package com.thanhsang.bookingapp.Models.Weather;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "city")
@Data
@AllArgsConstructor
public class CityModel {
    
    @Id
    @Column(name = "id", length = 20)
    private String id;

    @Column(name = "avatar", nullable = false, unique = true, length = 255)
    private String avatar;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;
}
