package com.thanhsang.bookingapp.Repositories.Hotel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thanhsang.bookingapp.Models.Hotel.HotelModel;

@Repository
public interface HotelRepository extends JpaRepository<HotelModel, Long>{
    
    @Query(value = "SELECT COUNT(id) FROM hotel WHERE id = ?1", nativeQuery = true)
    public Integer totalHotelsByCity(String city_id) throws Exception;

    @Query(value = "SELECT * FROM hotel WHERE city_id = ?1 And is_active = true", nativeQuery = true)
    public List<HotelModel> findAllByIdCityIsActive(String city_id) throws Exception;
}
