package com.thanhsang.bookingapp.Models.Hotel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hotel_images")
@IdClass(HotelImageKey.class)
@Data
@NoArgsConstructor
public class HotelImagesModel implements Serializable {
    
    @Id
    @Column(name = "hotel_id")
    private Long hotel_id;
    @Id
    @Column(name = "image_id")
    private Long image_id;
    
    private String url;

    public HotelImagesModel(Long hotel_id, Long image_id, String url) {
        this.hotel_id = hotel_id;
        this.image_id = image_id;
        this.url = url;
    }
}
