package com.thanhsang.bookingapp.Interfaces.Hotel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.thanhsang.bookingapp.Models.Addons.ResponseObject;
import com.thanhsang.bookingapp.Models.Hotel.RoomModel;

public interface RoomInterface {
    
    public ResponseEntity<ResponseObject> insert(RoomModel room, MultipartFile avatar, String access_token, String ipClient) throws Exception;
    public ResponseEntity<ResponseObject> update(RoomModel room, String access_token, String ipClient) throws Exception;
    public ResponseEntity<ResponseObject> stopActive(Long room_id, String access_token, String ipClient) throws Exception;
    public ResponseEntity<ResponseObject> backActive(Long room_id, String access_token, String ipClient) throws Exception;
    public ResponseEntity<ResponseObject> getRoomsByIdHotel(Long hotel_id) throws Exception;
    public ResponseEntity<ResponseObject> getRoomsByIdHotelIsActive(Long hotel_id) throws Exception;
    public ResponseEntity<ResponseObject> getARoomById(Long hotel_id) throws Exception;

}
