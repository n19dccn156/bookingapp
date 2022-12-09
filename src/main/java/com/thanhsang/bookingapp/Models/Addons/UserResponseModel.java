package com.thanhsang.bookingapp.Models.Addons;

import com.thanhsang.bookingapp.Models.User.UserModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseModel {
    public String role_name;
    public String firstname;
    public String lastname;
    public String gender;
    public String phone;
    public String avatar;
    public String email;

    public void setUser(UserModel user, String role_name) {
        this.role_name = role_name;
        this.firstname = user.firstname;
        this.lastname = user.lastname;
        this.gender = user.gender;
        this.phone = user.phone;
        this.avatar = user.avatar;
        this.email = user.email;
    }
}
