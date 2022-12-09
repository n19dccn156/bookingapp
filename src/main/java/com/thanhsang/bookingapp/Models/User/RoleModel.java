package com.thanhsang.bookingapp.Models.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "role")
@Data
public class RoleModel {
    
    @Id
    private String id;

    @Column(name = "name", nullable = false, unique = true, length = 20)
    private String name;
}
