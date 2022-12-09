package com.thanhsang.bookingapp.Models.Booking;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "status_booking")
@Data
@AllArgsConstructor
public class StatusBookingModel {
    
    @Id
    private String id;

    @Column(name = "name", nullable = false, unique = true, length = 20)
    private String name;

    @Column(name = "index", nullable = false, unique = true)
    private Integer index;
}
