package com.thanhsang.bookingapp.Controllers.User;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.thanhsang.bookingapp.Models.Addons.ResponseObject;
import com.thanhsang.bookingapp.Models.Addons.UserRequestModel;
import com.thanhsang.bookingapp.Repositories.User.UserRepository;
import com.thanhsang.bookingapp.Services.User.UserService;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {
    
    @Autowired UserService userService;
    @Autowired UserRepository userRepository;

    @PostMapping(value = "")
    public ResponseEntity<ResponseObject> insertUser(
        @ModelAttribute UserRequestModel user, 
        @RequestParam(name = "avatar", required = true) MultipartFile avatar) {
        try {
            return userService.insertUser(user, avatar);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject("failed", "Lỗi truy cập", "")
            );
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseObject> selectById(@PathVariable("id") Long id) {
        try {
            return userService.selectUserById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject("failed", "Lỗi truy cập", "")
            );
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<ResponseObject> login(
        @RequestParam(name = "username", required = true) String username,
        @RequestParam(name = "password", required = true) String password,
        HttpServletRequest request) {
        try {
            return userService.login(username, password, request.getRemoteAddr());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject("failed", "Lỗi truy cập", "")
            );
        }
    }
}
