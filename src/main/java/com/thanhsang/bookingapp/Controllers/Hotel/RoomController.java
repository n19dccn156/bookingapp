package com.thanhsang.bookingapp.Controllers.Hotel;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.thanhsang.bookingapp.Models.Addons.ResponseObject;
import com.thanhsang.bookingapp.Models.Hotel.RoomModel;
import com.thanhsang.bookingapp.Services.Hotel.RoomService;

@RestController
@RequestMapping(value = "/api/v1/rooms")
public class RoomController {

    @Autowired RoomService roomService;
    
    @PostMapping(value = "")
    public ResponseEntity<ResponseObject> insert(
        @ModelAttribute RoomModel room, 
            @RequestParam(name = "image", required = true) MultipartFile image,
                @RequestParam(name = "access_token", required = true) String access_token, 
                    HttpServletRequest request) {

        return roomService.insert(room, image, access_token, request.getRemoteAddr());
    }

    @PatchMapping(value = "")
    public ResponseEntity<ResponseObject> update(
        @RequestBody RoomModel room, 
            @RequestParam(name = "access_token", required = true) String access_token, 
                HttpServletRequest request) {
        return roomService.update(room, access_token, request.getRemoteAddr());
    }

    @PatchMapping(value = "/{room_id}/stop")
    public ResponseEntity<ResponseObject> stopActive(
        @PathVariable("room_id") Long room_id, 
            @RequestParam(name = "access_token", required = true) String access_token, 
                HttpServletRequest request) {
        return roomService.stopActive(room_id, access_token, request.getRemoteAddr());
    }

    @PatchMapping(value = "/{room_id}/back")
    public ResponseEntity<ResponseObject> backActive(
        @PathVariable("room_id") Long room_id, 
            @RequestParam(name = "access_token", required = true) String access_token, 
                HttpServletRequest request) {
        return roomService.backActive(room_id, access_token, request.getRemoteAddr());
    }

    @GetMapping(value = "/hotel/{id}")
    public ResponseEntity<ResponseObject> getRoomsByIdHotel(@PathVariable("id") Long hotel_id) {
        return roomService.getRoomsByIdHotel(hotel_id);
    }

    @GetMapping(value = "/hotel/{id}/active")
    public ResponseEntity<ResponseObject> getRoomsByIdHotelIsActive(@PathVariable("id") Long hotel_id) {
        return roomService.getRoomsByIdHotelIsActive(hotel_id);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseObject> getARoomById(@PathVariable("id") Long id) {
        return roomService.getARoomById(id);
    }
}
