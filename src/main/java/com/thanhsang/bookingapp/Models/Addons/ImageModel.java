package com.thanhsang.bookingapp.Models.Addons;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "images")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageModel {
    
    @Id
    private Long id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Lob
    @Column(name = "data", nullable = false)
    private byte[] data;

}
