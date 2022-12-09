package com.thanhsang.bookingapp.Models.Hotel;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConvenientKey implements Serializable {
    
    private Long hotel_id;
    private Long convenient_id;
}
