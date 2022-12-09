package com.thanhsang.bookingapp.Models.Hotel;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelImageKey implements Serializable {
    
    private Long hotel_id;
    private Long image_id;
}
