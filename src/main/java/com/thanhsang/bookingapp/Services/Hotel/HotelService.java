package com.thanhsang.bookingapp.Services.Hotel;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.thanhsang.bookingapp.Interfaces.Hotel.HotelInteface;
import com.thanhsang.bookingapp.Models.Addons.ImageModel;
import com.thanhsang.bookingapp.Models.Addons.ResponseObject;
import com.thanhsang.bookingapp.Models.Hotel.HotelImagesModel;
import com.thanhsang.bookingapp.Models.Hotel.HotelModel;
import com.thanhsang.bookingapp.Repositories.Hotel.HotelImagesRepository;
import com.thanhsang.bookingapp.Repositories.Hotel.HotelRepository;
import com.thanhsang.bookingapp.Repositories.User.UserRepository;
import com.thanhsang.bookingapp.Services.Addons.ImageService;
import com.thanhsang.bookingapp.Utils.GenerateTime;
import com.thanhsang.bookingapp.Utils.JWTProvider;

@Service
public class HotelService implements HotelInteface{

    @Autowired JWTProvider jwtProvider;
    @Autowired ImageService imageService;
    @Autowired UserRepository userRepository;
    @Autowired HotelRepository hotelRepository;
    @Autowired HotelImagesRepository hotelImagesRepository;

    @Override
    public ResponseEntity<ResponseObject> insert(HotelModel hotel, String access_token, String ipClient) {
        try {
            String constraint = hotel.checkContraints();
            if(!constraint.equals("OK")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("failed", constraint, "")
                );
            }
            String status = jwtProvider.validateRole(access_token, "ADMIN", ipClient);
            if(!status.equals("OK")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new ResponseObject("failed", status, "")
                );
            } else {
                hotel.insert();
                hotelRepository.save(hotel);
                return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ResponseObject("success", "Thêm thành công", hotel)
                ); 
            }   
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject("failed", "Không thể truy cập", "") 
            );
        }       
    }

    @Override
    public ResponseEntity<ResponseObject> update(HotelModel hotel, String access_token, String ipClient) {
        try {
            String constraint = hotel.checkContraints();
            if(!constraint.equals("OK")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("failed", constraint, "")
                );
            }
            String status = jwtProvider.validateRole(access_token, "HOTEL", ipClient);
            if(!status.equals("OK")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new ResponseObject("failed", status, "")
                );
            } else {
                Optional<HotelModel> foundHotel = hotelRepository.findById(hotel.getId());
                if(foundHotel.isPresent()) {
                    foundHotel.get().update(hotel);
                    hotelRepository.save(foundHotel.get());
                    return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("success", "Cập nhật thành công", foundHotel) 
                    );
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cập nhật Thất bại", "") 
                    );
                }
            }   
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject("failed", "Không thể truy cập", "") 
            );
        }            
    }

    @Override
    public ResponseEntity<ResponseObject> stop_active(Long id, String access_token, String ipClient) {
        try {
            String status = jwtProvider.validateRole(access_token, "ADMIN", ipClient);
            if(!status.equals("OK")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new ResponseObject("failed", status, "")
                );
            } else {
                Optional<HotelModel> foundHotel = hotelRepository.findById(id);
                if(foundHotel.isPresent()) {
                    foundHotel.get().setIs_active(false);
                    hotelRepository.save(foundHotel.get());
                    return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("success", "Cập nhật thành công", foundHotel) 
                    );
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cập nhật Thất bại", "") 
                    );
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject("failed", "Không thể truy cập", "") 
            );
        }
    }

    @Override
    public ResponseEntity<ResponseObject> back_active(Long id, String access_token, String ipClient) {
        try {
            String status = jwtProvider.validateRole(access_token, "ADMIN", ipClient);
            if(!status.equals("OK")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new ResponseObject("failed", status, "")
                );
            } else {
                Optional<HotelModel> foundHotel = hotelRepository.findById(id);
                if(foundHotel.isPresent()) {
                    foundHotel.get().setIs_active(true);
                    foundHotel.get().setCreated(GenerateTime.getCurrentTimeStamp());
                    hotelRepository.save(foundHotel.get());
                    return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("success", "Cập nhật thành công", foundHotel) 
                    );
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cập nhật Thất bại", "") 
                    );
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject("failed", "Không thể truy cập", "") 
            );
        }    
    }

    @Override
    public ResponseEntity<ResponseObject> updateImage(Long id, String access_token, 
        MultipartFile[] images, String ipClient) {
        try {
            String status = jwtProvider.validateRole(access_token, "HOTEL", ipClient);
            if(!status.equals("OK")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new ResponseObject("failed", status, "")
                );
            } else {
                Optional<HotelModel> foundHotel = hotelRepository.findById(id);
                if(foundHotel.isPresent()) {
                    String avatar = "";
                    for (MultipartFile file : images) {
                        ImageModel image = imageService.insert(file);
                        String avatarURL = ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                                .path("/api/v1/images/")
                                    .path(Long.toString(image.getId()))
                                        .toUriString();
                        HotelImagesModel hote_image = new HotelImagesModel(id, image.getId(), avatarURL);
                        hotelImagesRepository.save(hote_image);
                        avatar = avatarURL;
                    }
                    foundHotel.get().setAvatar(avatar);
                    hotelRepository.save(foundHotel.get());
                    return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("success", "Cập nhật thành công", foundHotel) 
                    );
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cập nhật Thất bại", "") 
                    );
                }
            }  
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject("failed", "Lỗi truy cập", "") 
            );
        }
    }
    
    @Override
    public ResponseEntity<ResponseObject> getAnHotel(Long id) {
        try {
            Optional<HotelModel> foundHotel = hotelRepository.findById(id);
            return foundHotel.isPresent() ? 
                ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Tìm thành công", foundHotel.get())
                )
                :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Không tìm thấy", "")
                );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject("failed", "Lỗi truy cập", "")
            );
        }
    }

    @Override
    public ResponseEntity<ResponseObject> getImages(Long id) {
        try {
            List<HotelImagesModel> images = hotelImagesRepository.listImages(id);
            return !images.isEmpty() ?
                ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Tìm thành công", images)
                )
                :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Không tìm thấy", images)
                );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject("failed", "Lỗi truy cập", "")
            );        
        }
    }

    @Override
    public ResponseEntity<ResponseObject> getHotelsByCity(String city_id) {
        try {
            List<HotelModel> hotels = hotelRepository.findAllByIdCityIsActive(city_id);
            return !hotels.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Tìm thành công", hotels)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Không tìm thấy", hotels)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject("failed", "Lỗi truy cập", "")
            );      
        }
    }

}
