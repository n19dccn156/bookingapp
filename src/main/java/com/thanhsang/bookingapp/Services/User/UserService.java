package com.thanhsang.bookingapp.Services.User;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.thanhsang.bookingapp.Interfaces.User.UserInteface;
import com.thanhsang.bookingapp.Models.Addons.ImageModel;
import com.thanhsang.bookingapp.Models.Addons.ResponseObject;
import com.thanhsang.bookingapp.Models.Addons.UserRequestModel;
import com.thanhsang.bookingapp.Models.Addons.UserResponseModel;
import com.thanhsang.bookingapp.Models.User.RoleModel;
import com.thanhsang.bookingapp.Models.User.UserModel;
import com.thanhsang.bookingapp.Repositories.User.RoleRepository;
import com.thanhsang.bookingapp.Repositories.User.UserRepository;
import com.thanhsang.bookingapp.Services.Addons.ImageService;
import com.thanhsang.bookingapp.Utils.GenerateId;
import com.thanhsang.bookingapp.Utils.GenerateTime;
import com.thanhsang.bookingapp.Utils.JWTProvider;

@Service
public class UserService implements UserInteface{

    @Autowired ImageService imageService;
    @Autowired UserRepository userRepository;
    @Autowired JWTProvider jwtProvider;
    @Autowired RoleRepository roleRepository;

    @Override
    public ResponseEntity<ResponseObject> insertUser(UserRequestModel user, MultipartFile avatar) throws Exception {
        try {
            UserModel userModel = new UserModel(
                GenerateId.generateId(), 
                    user.getRole_id(), 
                        user.getUsername().toLowerCase().trim(), 
                            user.getPassword(), 
                                user.getFirstname(), 
                                    user.getLastname(), 
                                        user.getGender(), 
                                            user.getPhone(), 
                                                "http://localhost:8080/api/v1/images/1", 
                                                    user.getEmail(), 
                                                        GenerateTime.getCurrentTimeStamp());
            String statusCheck = userModel.checkContraints();
            if(!statusCheck.equals("OK")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("failed", statusCheck, "")
                );
            }
            if(avatar != null) {
                ImageModel image = imageService.insert(avatar);
                String avatarURL = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                        .path("/api/v1/images/")
                            .path(Long.toString(image.getId()))
                                .toUriString();
                userModel.setAvatar(avatarURL);
            }
            userRepository.save(userModel);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject("success", "Tạo tài khoản thành công", userModel)
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject("failed", "Lỗi truy cập", "")
            );
        }
    }

    @Override
    public ResponseEntity<ResponseObject> login(String username, String password, String ipClient) throws Exception {
        
        Optional<UserModel> foundUser = userRepository.findByUsernameAndPassword(username.toLowerCase().trim(), password.trim());

        if(foundUser.isPresent()) {
            String access_token = jwtProvider.generateToken(foundUser.get().getId(), ipClient);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=UTF-8");
            headers.add("Bearer", access_token);

            return ResponseEntity.status(HttpStatus.OK)
            .headers(headers)
            .body(
                new ResponseObject("success", "Đăng nhập thành công", foundUser.get().getId())
            );
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ResponseObject("failed", "Tài khoản hoặc mật khẩu sai", "")
            );
        }
    }

    @Override
    public ResponseEntity<ResponseObject> selectUserById(Long id) throws Exception {

        Optional<UserModel> foundUser = userRepository.findById(id);
        UserResponseModel user = new UserResponseModel();
        if(foundUser.isPresent()) {
            Optional<RoleModel> foundRole = roleRepository.findById(foundUser.get().getRole_id());
            user.setUser(foundUser.get(), foundRole.get().getName());
        }
        return foundUser.isPresent() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Tìm tài khoản thành công", user)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Không tìm thấy tài khoản", "")
            );
    }

    @Override
    public Long selectUserIdByToken(String token, String ipClient) throws Exception {
        
        String status = jwtProvider.validateToken(token);
        if(!status.equals("OK")) {
            return 0L;
        } else {
            try {
                String data[] = jwtProvider.getUserIdAndIpClientFromJWT(token);
                if(!data[1].equals(ipClient)) return 0L;
                return Long.valueOf(data[0]);
            } catch (Exception e) {
                return 0L;
            }
        }
    }
    
}
