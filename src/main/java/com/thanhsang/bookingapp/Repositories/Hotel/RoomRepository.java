package com.thanhsang.bookingapp.Repositories.Hotel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thanhsang.bookingapp.Models.Hotel.RoomModel;

@Repository
public interface RoomRepository extends JpaRepository<RoomModel, Long>{
    
    @Query(value = "SELECT * FROM room WHERE hotel_id = ?1", nativeQuery = true)
    public List<RoomModel> findRoomByIdHotel(Long hotel_id);

    @Query(value = "SELECT * FROM room WHERE hotel_id = ?1 and is_active = true", nativeQuery = true)
    public List<RoomModel> findRoomByIdHotelIsActive(Long hotel_id);
}
