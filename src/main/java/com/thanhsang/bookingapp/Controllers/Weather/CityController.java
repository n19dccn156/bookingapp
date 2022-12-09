package com.thanhsang.bookingapp.Controllers.Weather;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thanhsang.bookingapp.Models.Addons.ResponseObject;
import com.thanhsang.bookingapp.Services.Addons.ListCityHomeService;

@RestController
@RequestMapping(value = "/api/v1/citys")
public class CityController {

    @Autowired ListCityHomeService listCityHomeService;
    
    @GetMapping(value = "/hotel")
    public ResponseEntity<ResponseObject> getCitysHotel() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Tìm kiếm thành công", listCityHomeService.getAllCityAndTotalHotel())
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject("failed", "Lỗi truy cập", new ArrayList<>())
            );
        }
    }
}
