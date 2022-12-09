package com.thanhsang.bookingapp.Models.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    
    @Id
    public Long id;

    @Column(name = "role_id", nullable = false, length = 10)
    public String role_id;

    @Column(name = "username", length = 20, unique = true, nullable = false)
    public String username;
    
    @Column(name = "password", length = 20, nullable = false)
    public String password;
    
    @Column(name = "firstname", length = 20, nullable = false)
    public String firstname;

    @Column(name = "lastname", length = 20, nullable = false)
    public String lastname;

    @Column(name = "gender", length = 5, nullable = false)
    public String gender;

    @Column(name = "phone", length = 20, nullable = false)
    public String phone;

    @Column(name = "avatar", length = 255, nullable = false)
    public String avatar;

    @Column(name = "email", length = 255, unique = true, nullable = false)
    public String email;

    @Column(name = "created", nullable = false, length = 50)
    private String created;

    public String checkContraints() {
        try {
            if(this.getUsername().length() < 5 && this.getUsername().length() > 20 ) return "Tài khoản phải từ 5 - 20 ký tự";
            else if(this.getPassword().length() < 5 && this.getPassword().length() > 20 ) return "Mật khẩu phải từ 5 - 20 ký tự";
            else if(this.getFirstname().isEmpty()) return "Họ không được bỏ trống";
            else if(this.getLastname().isEmpty()) return "Tên không được bỏ trống";
            else if(!this.getGender().equals("Nam") && !this.getGender().equals("Nữ") && !this.getGender().equals("Khác")) return "Giới tính phải là 'Nam', 'Nữ' hoặc 'Khác'";
            else if(this.getPhone().length() != 10) return "Số điện thoại phải có 10 ký tự";
            else if(this.getEmail().isEmpty()) return "Email không được bỏ trống";
            else if(this.getEmail().split("@")[1].isEmpty()) return "Email không đúng định dạng";   
            return "OK";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Email không đúng định dạng";
        }
    }

    public void updateInformation(UserModel user) {
        this.setFirstname(user.getFirstname());
        this.setLastname(user.getLastname());
        this.setGender(user.getGender());
        this.setPhone(user.getPhone());
    }

}
