package com.thanhsang.bookingapp.Controllers.Addons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.thanhsang.bookingapp.Models.Addons.ImageModel;
import com.thanhsang.bookingapp.Models.Addons.ResponseObject;
import com.thanhsang.bookingapp.Services.Addons.ImageService;


@RestController
@RequestMapping(value = "/api/v1/images")
public class ImageController {

    @Autowired ImageService imageService;
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) {
        
        try {
            ImageModel foundImage = imageService.selectById(id);
            return ResponseEntity.status(HttpStatus.OK)
            .contentType(MediaType.IMAGE_JPEG)
            // use for download image
            // .header(HttpHeaders.CONTENT_DISPOSITION, 
            //         "attachment; filename=\"" + foundImage.getId() + "\"")
            .body(foundImage.getData());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                
            .body(
                "".getBytes()
            );
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<ResponseObject> insert(@RequestParam(name = "image", required = true) MultipartFile image) {
        try {
            ImageModel data = imageService.insert(image);
            return ResponseEntity
                .status(HttpStatus.CREATED)
                    // .contentType(MediaType.i)
                        .body(
                            new ResponseObject("success", "Thêm hình thành công", data.getId())
                        );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject("failed", "Thêm hình thất bại", "")
            );
        }
    }
}
