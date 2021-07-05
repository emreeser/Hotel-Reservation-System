package com.HotelReservation.hoteldetailsmicroservice.repository;

import com.HotelReservation.hoteldetailsmicroservice.model.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends MongoRepository<Hotel,String> {
    public Hotel findByName(String name);
    public List<Hotel> findByCity(String city);
}
