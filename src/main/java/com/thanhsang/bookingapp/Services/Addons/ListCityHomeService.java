package com.thanhsang.bookingapp.Services.Addons;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanhsang.bookingapp.Models.Addons.ListCityHome;
import com.thanhsang.bookingapp.Models.Weather.CityModel;
import com.thanhsang.bookingapp.Repositories.Hotel.HotelRepository;
import com.thanhsang.bookingapp.Repositories.Weather.CityRepository;

@Service
public class ListCityHomeService {
    
    @Autowired CityRepository cityRepository;
    @Autowired HotelRepository hotelRepository;

    public List<ListCityHome> getAllCityAndTotalHotel() throws Exception {
        List<CityModel> citys = cityRepository.findAll();
        List<ListCityHome> result = new ArrayList<>();

        for (CityModel cityModel : citys) {
            Integer total = hotelRepository.totalHotelsByCity(cityModel.getId());
            result.add(new ListCityHome(cityModel.getId(), cityModel.getName(), cityModel.getAvatar(), total));
        }

        return result;
    }
}
