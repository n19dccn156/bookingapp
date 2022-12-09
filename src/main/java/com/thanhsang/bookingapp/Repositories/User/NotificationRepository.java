package com.thanhsang.bookingapp.Repositories.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhsang.bookingapp.Models.User.NotificationModel;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationModel, Long>{
    
}
