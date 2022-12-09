package com.thanhsang.bookingapp.Repositories.Addons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhsang.bookingapp.Models.Addons.ImageModel;

@Repository
public interface ImageRepository extends JpaRepository<ImageModel, Long>{
    
    
}
