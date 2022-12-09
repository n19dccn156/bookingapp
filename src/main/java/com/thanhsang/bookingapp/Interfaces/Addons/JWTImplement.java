package com.thanhsang.bookingapp.Interfaces.Addons;

public interface JWTImplement {

    public String generateToken(Long user_id, String ipClient);
    public String[] getUserIdAndIpClientFromJWT(String token);
    public String validateToken(String authToken);
    public String validateRole(String access_token, String role, String ipClient);
    // public Boolean validateRoleHotel();

}
