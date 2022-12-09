package com.thanhsang.bookingapp.Models.Hotel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.thanhsang.bookingapp.Utils.GenerateId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "room")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomModel {
    
    @Id
    private Long id;

    @Column(name = "hotel_id", nullable = false)
    private Long hotel_id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "avatar", nullable = false, length = 255)
    private String avatar;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "is_active", nullable = false)
    private Boolean is_active;

    public String checkContraints() {
        if(this.getName().length() < 5 && this.getName().length() > 50 ) return "Tên khách sạn phải từ 5 - 50 ký tự";
            else if(this.getQuantity() < 1) return "Số lượng phải lớn hơn 0";
                else if(this.getPrice() < 100000) return "Giá Phòng phải lớn hơn 100.000 VNĐ";
        return "OK";
    }

    public void insert() {
        this.id = GenerateId.generateId();
        this.avatar = "http://localhost:8080/api/v1/images/1";
        this.is_active = true;
    }

    public void update(RoomModel room) {
        this.name = room.getName();
        this.quantity = room.getQuantity();
        this.price = room.getPrice();
    }
}
