package com.HotelReservation.pricelistmicroservice.service;

import com.HotelReservation.pricelistmicroservice.model.Price;
import com.HotelReservation.pricelistmicroservice.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    public Price create(String roomType, Long roomPrice){
        return priceRepository.save(new Price(roomType,roomPrice));
    }

    public List<Price> getAll(){
        return priceRepository.findAll();
    }

    public Price getByRoomType(String roomType){
        return priceRepository.findByRoomType(roomType);
    }

    public Price update(String roomType,Long roomPrice){
        Price price = priceRepository.findByRoomType(roomType);
        price.setRoomPrice(roomPrice);
        return priceRepository.save(price);
    }

    public void deleteAll(){
        priceRepository.deleteAll();
    }

    public void delete(String roomType){
        Price price = priceRepository.findByRoomType(roomType);
        priceRepository.delete(price);
    }
}
