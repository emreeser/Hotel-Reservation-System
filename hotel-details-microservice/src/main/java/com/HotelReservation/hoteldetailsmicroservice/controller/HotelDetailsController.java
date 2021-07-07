package com.HotelReservation.hoteldetailsmicroservice.controller;

import com.HotelReservation.hoteldetailsmicroservice.model.Hotel;
import com.HotelReservation.hoteldetailsmicroservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HotelDetailsController {

    @Autowired
    private HotelService hotelService;

    @RequestMapping("/create")
    public String create(@RequestParam String name, @RequestParam String address,
                         @RequestParam String city, @RequestParam String country,
                         @RequestParam Long phoneNumber)
    {
        Hotel hotel = hotelService.create(name,address,city,country,phoneNumber);
        return hotel.toString();
    }

    @RequestMapping("/get")
    public Hotel getHotel(@RequestParam String name){
        return hotelService.getByName(name);
    }

    @RequestMapping("/getAll")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Hotel> getAll(){
        return hotelService.getAll();
    }

    @RequestMapping("/update")
    public String update(@RequestParam String name, @RequestParam String address,
                        @RequestParam String city, @RequestParam String country,
                        @RequestParam Long phoneNumber)
    {
        Hotel hotel = hotelService.update(name,address,city,country,phoneNumber);
        return hotel.toString();
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam String name){
        hotelService.deleteByName(name);
        return name + " : Hotel Details Deleted!";
    }

    @RequestMapping("/deleteAll")
    public String deleteAll(){
        hotelService.deleteAll();
        return "All Hotel Details Deleted!";
    }
}
