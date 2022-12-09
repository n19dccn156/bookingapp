package com.thanhsang.bookingapp.Models.Booking;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "booking_detail")
@IdClass(BookingDetailKey.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDetailModel {
    
    @Id
    private Long booking_id;

    @Id
    private Long room_id;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public String checkContraints() {
        if(this.price <= 100000) return "Giá thấp nhất là 100.000 VNĐ";
        if(this.quantity <= 1) return "Số lượng phải lớn hơn 0";
        return "OK";
    }

}
