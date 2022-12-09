package com.thanhsang.bookingapp.Services.Hotel;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.thanhsang.bookingapp.Interfaces.Hotel.RoomInterface;
import com.thanhsang.bookingapp.Models.Addons.ImageModel;
import com.thanhsang.bookingapp.Models.Addons.ResponseObject;
import com.thanhsang.bookingapp.Models.Hotel.RoomModel;
import com.thanhsang.bookingapp.Repositories.Hotel.RoomRepository;
import com.thanhsang.bookingapp.Services.Addons.ImageService;
import com.thanhsang.bookingapp.Utils.JWTProvider;

@Service
public class RoomService implements RoomInterface{

    @Autowired RoomRepository roomRepository;
    @Autowired JWTProvider jwtProvider;
    @Autowired ImageService imageService;

    @Override
    public ResponseEntity<ResponseObject> insert(RoomModel room, MultipartFile avatar, String access_token, String ipClient) {
        try {
            String constraint = room.checkContraints();
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
                room.insert();
                if(avatar != null) {
                    ImageModel image = imageService.insert(avatar);
                    String avatarURL = ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                            .path("/api/v1/images/")
                                .path(Long.toString(image.getId()))
                                    .toUriString();
                    room.setAvatar(avatarURL);
                }
                roomRepository.save(room);
                return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ResponseObject("success", "Thêm thành công", room)
                ); 
            }   
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject("failed", "Không thể truy cập", "") 
            );
        }  
    }

    @Override
    public ResponseEntity<ResponseObject> update(RoomModel room, String access_token, String ipClient) {
        try {
            String constraint = room.checkContraints();
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
                Optional<RoomModel> foundRoom = roomRepository.findById(room.getId());
                if(foundRoom.isPresent()) {
                    foundRoom.get().update(room);
                    roomRepository.save(foundRoom.get());
                    return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("success", "Cập nhật thành công", foundRoom) 
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
    public ResponseEntity<ResponseObject> stopActive(Long room_id, String access_token, String ipClient) {
        try {
            String status = jwtProvider.validateRole(access_token, "HOTEL", ipClient);
            if(!status.equals("OK")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new ResponseObject("failed", status, "")
                );
            } else {
                Optional<RoomModel> foundRoom = roomRepository.findById(room_id);
                if(foundRoom.isPresent()) {
                    foundRoom.get().setIs_active(false);
                    roomRepository.save(foundRoom.get());
                    return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("success", "Cập nhật thành công", foundRoom.get()) 
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
    public ResponseEntity<ResponseObject> backActive(Long room_id, String access_token, String ipClient) {
        try {
            String status = jwtProvider.validateRole(access_token, "HOTEL", ipClient);
            if(!status.equals("OK")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new ResponseObject("failed", status, "")
                );
            } else {
                Optional<RoomModel> foundRoom = roomRepository.findById(room_id);
                if(foundRoom.isPresent()) {
                    foundRoom.get().setIs_active(true);
                    roomRepository.save(foundRoom.get());
                    return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("success", "Cập nhật thành công", foundRoom.get()) 
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
    public ResponseEntity<ResponseObject> getRoomsByIdHotel(Long hotel_id) {
        try {
            List<RoomModel> rooms = roomRepository.findRoomByIdHotel(hotel_id);
            return !rooms.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Tìm thành công", rooms)
            ):
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Không tìm thấy", rooms)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject("failed", "Lỗi truy cập", "")
            );        
        }
    }

    @Override
    public ResponseEntity<ResponseObject> getRoomsByIdHotelIsActive(Long hotel_id) {
        try {
            List<RoomModel> rooms = roomRepository.findRoomByIdHotelIsActive(hotel_id);
            return !rooms.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Tìm thành công", rooms)
            ):
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Không tìm thấy", rooms)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject("failed", "Lỗi truy cập", "")
            );        
        }
    }

    @Override
    public ResponseEntity<ResponseObject> getARoomById(Long hotel_id) {
        try {
            Optional<RoomModel> foundRoom = roomRepository.findById(hotel_id);
            return foundRoom.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Tìm thành công", foundRoom.get())
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Không tìm thấy", "")
                );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject("failed", "Lỗi truy cập", "")
            );
        }
    }
    
}
