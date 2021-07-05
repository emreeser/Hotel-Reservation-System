package com.HotelReservation.hoteldetailsmicroservice.service;

import com.HotelReservation.hoteldetailsmicroservice.model.Hotel;
import com.HotelReservation.hoteldetailsmicroservice.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public Hotel create(String name, String address, String city, String country, Long phoneNumber){
        return hotelRepository.save(new Hotel(name,address,city,country,phoneNumber));
    }

    public List<Hotel> getAll(){
        return hotelRepository.findAll();
    }

    public Hotel getByName(String name){
        return hotelRepository.findByName(name);
    }

    public Hotel update(String name, String address, String city, String country, Long phoneNumber){
        Hotel hotel = hotelRepository.findByName(name);
        hotel.setName(name);
        hotel.setAddress(address);
        hotel.setCity(city);
        hotel.setCountry(country);
        hotel.setPhoneNumber(phoneNumber);

        return hotelRepository.save(hotel);
    }

    public void deleteAll(){
        hotelRepository.deleteAll();
    }

    public void deleteByName(String name){
        Hotel hotel = hotelRepository.findByName(name);
        hotelRepository.delete(hotel);
    }
}
