package com.thanhsang.bookingapp.Interfaces.Addons;

import org.springframework.web.multipart.MultipartFile;

import com.thanhsang.bookingapp.Models.Addons.ImageModel;

public interface ImageInterface {
    
    public ImageModel selectById(Long id) throws Exception;
    public ImageModel insert(MultipartFile image) throws Exception;
    // public ResponseEntity<ResponseObject> update(ImageModel image) throws Exception;
}
