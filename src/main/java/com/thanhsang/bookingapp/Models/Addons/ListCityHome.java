package com.thanhsang.bookingapp.Models.Addons;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListCityHome {
    
    private String city_id;
    private String city_name;
    private String avatar;
    private Integer hotels_total;
}
