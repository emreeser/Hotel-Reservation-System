package com.HotelReservation.bookingmanagermicroservice.service;

import com.HotelReservation.bookingmanagermicroservice.model.Booking;
import com.HotelReservation.bookingmanagermicroservice.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking create(String custFullName, String custPhone, String custUserID, int totalNights){

        return bookingRepository.save(new Booking(custFullName,custPhone,custUserID,totalNights));
    }

    public List<Booking> getAll(){ return bookingRepository.findAll(); }

    public Booking getByCustFullName(String custFullName){
        return bookingRepository.findByCustFullName(custFullName);
    }

    public Booking update(String custFullName, String custPhone, String custUserID, int totalNights){
        Booking booking = bookingRepository.findByCustFullName(custFullName);
        booking.setCustPhone(custPhone);
        booking.setCustUserId(custUserID);
        booking.setTotalNights(totalNights);

        return bookingRepository.save(booking);
    }
    public void deleteAll(){bookingRepository.deleteAll();}

    public void delete(String custFullName){
        Booking booking = bookingRepository.findByCustFullName(custFullName);
        bookingRepository.delete(booking);
    }

}
