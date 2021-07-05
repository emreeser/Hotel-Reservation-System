package com.HotelReservation.bookingmanagermicroservice.repository;

import com.HotelReservation.bookingmanagermicroservice.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingRepository extends MongoRepository<Booking,String> {

    public Booking findByCustFullName(String custFullName);
    public List<Booking> findByCustUserId(String custUserId);
}
