package com.HotelReservation.bookingmanagermicroservice.controller;

import com.HotelReservation.bookingmanagermicroservice.model.Booking;
import com.HotelReservation.bookingmanagermicroservice.model.Price;
import com.HotelReservation.bookingmanagermicroservice.service.BookingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class BookingController {
    public int totprice;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/priceList")
    public String getPrice(@RequestParam String roomType)
    {
        String url = "http://price-list-service/get?roomType="+roomType;

        String str = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();

        int price=0;

        try {
            Price p = mapper.readValue(str, Price.class);

            price = Integer.parseInt(p.getRoomPrice());

            //System.out.println(price);

        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        this.totprice = price;

        int finalPrice = Booking.nights * price;

        return "$ "+price+"/Night" + " | Total price = $"+finalPrice ;

    }

    @RequestMapping("/create")
    public  String create(@RequestParam String custFullName, @RequestParam String custPhone,
                          @RequestParam String custUserID, @RequestParam int totalNights)
    {
        Booking booking = bookingService.create(custFullName,custPhone,custUserID,totalNights);
        return booking.toString();
    }

    @RequestMapping("/get")
    public Booking getBooking(@RequestParam String custFullName){
        return bookingService.getByCustFullName(custFullName);
    }

    @RequestMapping("/getAll")
    public List<Booking> getAll(){
        return bookingService.getAll();
    }

    @RequestMapping("/update")
    public  String update(@RequestParam String custFullName, @RequestParam String custPhone,
                          @RequestParam String custUserID, @RequestParam int totalNights)
    {
        Booking booking = bookingService.update(custFullName,custPhone,custUserID,totalNights);
        return booking.toString();
    }

    @RequestMapping("/deleteAll")
    public String deleteAll(){
        bookingService.deleteAll();
        return "All booking details deleted!";
    }
}
