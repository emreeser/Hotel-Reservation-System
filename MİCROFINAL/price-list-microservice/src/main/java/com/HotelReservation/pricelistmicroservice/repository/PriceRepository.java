package com.HotelReservation.pricelistmicroservice.repository;

import com.HotelReservation.pricelistmicroservice.model.Price;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface PriceRepository extends MongoRepository<Price,String> {

    public Price findByRoomType(String roomType);
    public List<Price> findByRoomPrice(Long roomPrice);

}
