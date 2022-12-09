package com.thanhsang.bookingapp.Models.Addons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestModel {

    public String role_id;
    public String username;
    public String password;
    public String firstname;
    public String lastname;
    public String gender;
    public String phone;
    public String email;

}
