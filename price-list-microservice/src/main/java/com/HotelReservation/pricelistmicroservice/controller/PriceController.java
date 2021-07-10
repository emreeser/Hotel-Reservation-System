package com.HotelReservation.pricelistmicroservice.controller;

import com.HotelReservation.pricelistmicroservice.model.Price;
import com.HotelReservation.pricelistmicroservice.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PriceController {

    @Autowired
    private PriceService priceService;

    @RequestMapping("/create")
    public String create(@RequestParam String roomType, @RequestParam Long roomPrice){
        Price price = priceService.create(roomType,roomPrice);
        return price.toString();
    }

    @RequestMapping("/get")
    public Price getPrice(@RequestParam String roomType){
        return priceService.getByRoomType(roomType);
    }

    @RequestMapping("/getAll")
    public List<Price> getAll(){
        return priceService.getAll();
    }

    @RequestMapping("/update")
    public String update(@RequestParam String roomType, @RequestParam Long roomPrice){
        Price price = priceService.update(roomType,roomPrice);
        return price.toString();
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam String roomType){
        priceService.delete(roomType);
        return roomType + " : Room Type Deleted!";
    }

    @RequestMapping("/deleteAll")
    public String deleteAll(){
        priceService.deleteAll();
        return "All Price Details Deleted!";
    }
}
