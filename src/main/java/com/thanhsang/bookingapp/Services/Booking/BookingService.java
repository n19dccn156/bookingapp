package com.thanhsang.bookingapp.Services.Booking;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.thanhsang.bookingapp.Interfaces.Booking.BookingInterface;
import com.thanhsang.bookingapp.Models.Addons.BookingRequest;
import com.thanhsang.bookingapp.Models.Addons.ResponseObject;
import com.thanhsang.bookingapp.Models.Booking.BookingDetailModel;
import com.thanhsang.bookingapp.Models.Booking.BookingModel;
import com.thanhsang.bookingapp.Repositories.Booking.BookingDetailRepository;
import com.thanhsang.bookingapp.Repositories.Booking.BookingRepository;
import com.thanhsang.bookingapp.Utils.JWTProvider;

@Service
public class BookingService implements BookingInterface {

    @Autowired JWTProvider jwtProvider;
    @Autowired BookingRepository bookingRepository;
    @Autowired BookingDetailRepository bookingDetailRepository;

    @Override
    public ResponseEntity<ResponseObject> insert(BookingRequest bookingRequest, String access_token) throws Exception {
        try {
            String constraint = bookingRequest.getBooking().checkContraints();
        
            if(!constraint.equals("OK")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("failed", constraint, "")
                );
            }
            if(bookingRequest.getBook_detail().size() < 1) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("failed", "Kiểm tra lại đơn", "")
                );
            }
            for (BookingDetailModel detail : bookingRequest.getBook_detail()) {
                String constraint2 = detail.checkContraints();
                if(!constraint2.equals("OK")) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new ResponseObject("failed", constraint2, "")
                    );
                }
            }
            String status = jwtProvider.validateToken(access_token);
            if(!status.equals("OK")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new ResponseObject("failed", status, "")
                );
            } else {
                bookingRequest.getBooking().insert();
                bookingRepository.save(bookingRequest.getBooking());
                for (BookingDetailModel detail : bookingRequest.getBook_detail()) {
                    bookingDetailRepository.save(detail);
                }
                return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ResponseObject("success", "Thêm thành công", bookingRequest)
                ); 
            }   
        } catch (Exception e) {
            bookingRepository.deleteById(bookingRequest.getBooking().getId());
            bookingDetailRepository.deleteByIdBooking(bookingRequest.getBooking().getId());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject("failed", "Không thể truy cập", "") 
            );
        }  
    }

    @Override
    public ResponseEntity<ResponseObject> updateState(Long booking_id, String state_id, String access_token, String ipClient) throws Exception {
        try {
            String status = jwtProvider.validateRole(access_token, "HOTEL", ipClient);
            if(!status.equals("OK")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new ResponseObject("failed", status, "")
                );
            } else {
                Optional<BookingModel> foundBooking = bookingRepository.findById(booking_id);
                if(foundBooking.isPresent()) {
                    foundBooking.get().setStatus_booking_id(state_id);
                    bookingRepository.save(foundBooking.get());
                    return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("success", "Cập nhật thành công", foundBooking.get()) 
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
    public ResponseEntity<ResponseObject> cancelBooking(Long booking_id, String access_token) {
        try {
            String status = jwtProvider.validateToken(access_token);
            if(!status.equals("OK")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new ResponseObject("failed", status, "")
                );
            } else {
                Optional<BookingModel> foundBooking = bookingRepository.findById(booking_id);
                if(foundBooking.isPresent() && foundBooking.get().getStatus_booking_id().equals("XACNHAN")) {
                    foundBooking.get().setStatus_booking_id("DAHUY");
                    bookingRepository.save(foundBooking.get());
                    return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("success", "Hủy đơn thành công", foundBooking) 
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
    public ResponseEntity<ResponseObject> getBooking(Long booking_id) {
        try {
            Optional<BookingModel> foundBooking = bookingRepository.findById(booking_id);
            if(foundBooking.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Tìm kiếm thất bại", "")  
                );
            }
            List<BookingDetailModel> list = bookingDetailRepository.findAllByIdBooking(booking_id);
            BookingRequest response = new BookingRequest(foundBooking.get(), list);
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Tìm thành công", response) 
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject("failed", "Không thể truy cập", "") 
            );
        }

    }
    
}
