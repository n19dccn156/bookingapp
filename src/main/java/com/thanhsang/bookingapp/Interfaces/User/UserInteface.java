package com.thanhsang.bookingapp.Interfaces.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.thanhsang.bookingapp.Models.Addons.ResponseObject;
import com.thanhsang.bookingapp.Models.Addons.UserRequestModel;

public interface UserInteface {
    
    public ResponseEntity<ResponseObject> insertUser(UserRequestModel user, MultipartFile avatar) throws Exception;
    public ResponseEntity<ResponseObject> login(String username, String password, String ipClient) throws Exception;
    public ResponseEntity<ResponseObject> selectUserById(Long id) throws Exception;
    public Long selectUserIdByToken(String token, String ipClient) throws Exception;


}
