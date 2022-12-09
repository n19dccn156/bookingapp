package com.thanhsang.bookingapp.Models.Booking;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rating")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingModel {

    @Id
    private Long booking_id;

    @Column(name = "star", nullable = false)
    private Integer star;

    @Column(name = "comment", nullable = false, length = 255)
    private String comment;

    @Column(name = "created", nullable = false, length = 20)
    private String created;
}
