package com.thanhsang.bookingapp.Models.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "notification")
@Data
public class NotificationModel {
 
    @Id
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Column(name = "created", nullable = false, length = 20)
    private String created;
}
