package com.thanhsang.bookingapp.Models.Hotel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.thanhsang.bookingapp.Utils.GenerateId;
import com.thanhsang.bookingapp.Utils.GenerateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hotel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelModel {
    
    @Id
    private Long id;
    
    @Column(name = "user_id", nullable = false, unique = true)
    private Long user_id;

    @Column(name = "city_id", nullable = false, length = 20)
    private String city_id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "avatar", nullable = false, length = 255)
    private String avatar;

    @Column(name = "price_min", nullable = false)
    private Integer price_min;

    @Column(name = "price_max", nullable = false)
    private Integer price_max;

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(name = "description", nullable = false, length = 1024)
    private String description;

    @Column(name = "star", nullable = false)
    private Integer star;

    @Column(name = "created", nullable = false, length = 50)
    private String created;

    @Column(name = "is_active", nullable = false)
    private Boolean is_active;

    public void update(HotelModel hotel) {
        this.city_id = hotel.getCity_id();
        this.name = hotel.getName();
        this.price_min = hotel.getPrice_min();
        this.price_max = hotel.getPrice_max();
        this.address = hotel.getAddress();
        this.description = hotel.getDescription();
    }

    public void insert() {
        this.setId(GenerateId.generateId());
        this.setStar(0);
        this.setAvatar("http://localhost:8080/api/v1/images/1");
        this.setIs_active(true);
        this.setCreated(GenerateTime.getCurrentTimeStamp());
    }

    public String checkContraints() {
        if(this.getName().length() < 5 && this.getName().length() > 50 ) return "Tên khách sạn phải từ 5 - 50 ký tự";
            else if(this.getPrice_min() < 100000) return "Giá nhỏ nhất phải lớn hơn 100.000 VNĐ";
            else if(this.getPrice_max() < 100000) return "Giá lớn nhất phải lớn hơn 100.000 VNĐ";
            else if(this.getAddress().isEmpty()) return "Địa chỉ không được bỏ trống";
            else if(this.getDescription().isEmpty()) return "Phần mô tả không được bỏ trống";
            return "OK";
    }
}
