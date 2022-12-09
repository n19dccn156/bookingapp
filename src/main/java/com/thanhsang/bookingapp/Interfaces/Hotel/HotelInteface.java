package com.thanhsang.bookingapp.Interfaces.Hotel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.thanhsang.bookingapp.Models.Addons.ResponseObject;
import com.thanhsang.bookingapp.Models.Hotel.HotelModel;

public interface HotelInteface {

    public ResponseEntity<ResponseObject> insert(HotelModel hotel, String access_token, String ipClient) throws Exception;
    public ResponseEntity<ResponseObject> update(HotelModel hotel, String access_token, String ipClient) throws Exception;
    public ResponseEntity<ResponseObject> stop_active(Long id, String access_token, String ipClient) throws Exception;
    public ResponseEntity<ResponseObject> back_active(Long id, String access_token, String ipClient) throws Exception;
    public ResponseEntity<ResponseObject> updateImage(Long id, String access_token, MultipartFile[] images, String ipClient) throws Exception;
    public ResponseEntity<ResponseObject> getImages(Long id) throws Exception;
    public ResponseEntity<ResponseObject> getAnHotel(Long id) throws Exception;
    public ResponseEntity<ResponseObject> getHotelsByCity(String id) throws Exception;

}
