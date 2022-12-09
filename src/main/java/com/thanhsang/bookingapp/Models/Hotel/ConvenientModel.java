package com.thanhsang.bookingapp.Models.Hotel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "convenient_detail")
@Data
@AllArgsConstructor
public class ConvenientModel {
    
    @Id
    private Long id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;
}
