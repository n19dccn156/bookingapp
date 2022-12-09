package com.thanhsang.bookingapp.Repositories.Hotel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thanhsang.bookingapp.Models.Hotel.HotelImageKey;
import com.thanhsang.bookingapp.Models.Hotel.HotelImagesModel;

@Repository
public interface HotelImagesRepository extends JpaRepository<HotelImagesModel, HotelImageKey>{
    
    @Query(value = "SELECT * FROM hotel_images WHERE hotel_id = ?1", nativeQuery = true)
    public List<HotelImagesModel> listImages(Long hotel_id) throws Exception;
}
