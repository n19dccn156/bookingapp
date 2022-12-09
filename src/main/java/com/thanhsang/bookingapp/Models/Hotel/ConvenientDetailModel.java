package com.thanhsang.bookingapp.Models.Hotel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "convenient_detail")
@IdClass(ConvenientKey.class)
@Data
@AllArgsConstructor
public class ConvenientDetailModel {
    
    @Id
    @Column(name = "hotel_id")
    private Long hotel_id;
    @Id
    @Column(name = "convenient_id")
    private Long convenient_id;
}
