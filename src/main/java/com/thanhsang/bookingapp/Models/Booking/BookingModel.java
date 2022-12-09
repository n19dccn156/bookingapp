package com.thanhsang.bookingapp.Models.Booking;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.thanhsang.bookingapp.Utils.GenerateId;
import com.thanhsang.bookingapp.Utils.GenerateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "booking")
@Data
@AllArgsConstructor
public class BookingModel {
    
    @Id
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @Column(name = "hotel_id", nullable = false)
    private Long hotel_id;

    @Column(name = "status_booking_id", nullable = false)
    private String status_booking_id;

    @Column(name = "checkin_date", nullable = false)
    private Date checkin_date;

    @Column(name = "checkout_date", nullable = false)
    private Date checkout_date;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "phone", nullable = false, length = 10)
    private String phone;

    @Column(name = "created", nullable = false, length = 20)
    private String created;

    public String checkContraints() {
        if(!this.checkin_date.before(this.checkout_date)) return "Bạn phải ở ít nhất một ngày";
        if(this.name.length() > 0) return "Tên không được bỏ trống";
        if(this.phone.length() == 10) return "Số điện thoại phải 10 số";
        return "OK";
    }

    public void insert() {
        this.id = GenerateId.generateId();
        this.status_booking_id= "XACNHAN";
        this.created = GenerateTime.getCurrentTimeStamp();
    }
}
