package com.thanhsang.bookingapp.Utils;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanhsang.bookingapp.Interfaces.Addons.JWTImplement;
import com.thanhsang.bookingapp.Models.User.UserModel;
import com.thanhsang.bookingapp.Repositories.User.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class JWTProvider implements JWTImplement{

    private final String JWT_SECRET = "bookingapp";
    @Autowired UserRepository userRepository;

    @Override
    public String generateToken(Long user_id, String ipClient) {
        Date expiryDate = new Date(GenerateTime.getCurrentTimeStampSecond() + GenerateTime.getCurrentTimeStampSecond1Hour());
        // Tạo chuỗi json web token từ id của user.
        return Jwts.builder()
            .setSubject(Long.toString(user_id) + "@" + ipClient)
                .setIssuedAt(new Date())
                    .setExpiration(expiryDate)
                        .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                            .compact();
    }
    
    @Override
    public String[] getUserIdAndIpClientFromJWT(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                    .getBody();

        return claims.getSubject().split("@");
    }

    @Override
    public String validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET)
                         .parseClaimsJws(authToken);
            return "OK";
        } catch (MalformedJwtException ex) {
            return "Không có quyền truy cập";
        } catch (ExpiredJwtException ex) {
            return "Hết phiên sử sử dụng, hãy đăng nhập lại";
        } catch (UnsupportedJwtException ex) {
            return "Không có quyền truy cập";
        } catch (IllegalArgumentException ex) {
            return "Không có quyền truy cập";
        }
    }
   
    @Override
    public String validateRole(String access_token, String role, String ipClient) {
        String status = validateToken(access_token);
        if(!status.equals("OK")) return status;

        try {
            String data[] = getUserIdAndIpClientFromJWT(access_token);
            Optional<UserModel> foundUser = userRepository.findById(Long.valueOf(data[0]));
            if(foundUser.isEmpty() || !data[1].equals(ipClient) || !foundUser.get().getRole_id().toUpperCase().equals(role)) {

                return "Không có quyền truy cập";
            }
            
            return "OK";
        } catch (Exception e) {
            return "Không có quyền truy cập";
        }
    }
}
