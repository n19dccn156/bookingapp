package com.thanhsang.bookingapp.Controllers.Hotel;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.thanhsang.bookingapp.Models.Addons.ResponseObject;
import com.thanhsang.bookingapp.Models.Hotel.HotelModel;
import com.thanhsang.bookingapp.Services.Hotel.HotelService;

@RestController
@RequestMapping(value = "/api/v1/hotels")
public class HotelController {

    @Autowired HotelService hotelService;
    
    @PostMapping(value = "")
    public ResponseEntity<ResponseObject> insert(@RequestBody HotelModel hotel,
    @RequestParam(name = "access_token", required = true) String access_token, HttpServletRequest request) {
        return hotelService.insert(hotel, access_token, request.getRemoteAddr());
    }

    @PatchMapping(value = "")
    public ResponseEntity<ResponseObject> update(@RequestBody HotelModel hotel,
    @RequestParam(name = "access_token", required = true) String access_token, HttpServletRequest request) {  
        return hotelService.update(hotel, access_token, request.getRemoteAddr());
    }
    
    @PatchMapping(value = "/{id}/stop")
    public ResponseEntity<ResponseObject> stop_active(@PathVariable("id") Long id, 
    @RequestParam(name = "access_token", required = true) String access_token, HttpServletRequest request) {
        return hotelService.stop_active(id, access_token, request.getRemoteAddr());
    }

    @PatchMapping(value = "/{id}/back")
    public ResponseEntity<ResponseObject> back_active(@PathVariable("id") Long id, 
    @RequestParam(name = "access_token", required = true) String access_token, HttpServletRequest request) {
        return hotelService.back_active(id, access_token, request.getRemoteAddr());
    }

    @PatchMapping(value = "/{id}/images")
    public ResponseEntity<ResponseObject> updateImage(@PathVariable("id") Long id, @RequestParam(name = "access_token", required = true) String access_token,
    @RequestParam(name = "images", required = true) MultipartFile[] images, HttpServletRequest request){
        return hotelService.updateImage(id, access_token, images, request.getRemoteAddr());
    }
    
    @GetMapping(value = "/{id}/images")
    public ResponseEntity<ResponseObject> getImages(@PathVariable("id") Long id) {
        return hotelService.getImages(id);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseObject> getAnHotel(@PathVariable("id") Long id) {
        return hotelService.getAnHotel(id);
    }

    @GetMapping(value = "/city/{city_id}")
    public ResponseEntity<ResponseObject> get(@PathVariable("city_id") String city_id) {
        return hotelService.getHotelsByCity(city_id);
    }

}
