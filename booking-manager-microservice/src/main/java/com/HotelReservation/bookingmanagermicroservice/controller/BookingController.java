package com.HotelReservation.bookingmanagermicroservice.controller;

import com.HotelReservation.bookingmanagermicroservice.model.Booking;
import com.HotelReservation.bookingmanagermicroservice.model.Price;
import com.HotelReservation.bookingmanagermicroservice.model.Room;
import com.HotelReservation.bookingmanagermicroservice.service.BookingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;

@RestController
public class BookingController {
    public int totprice;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/priceList")
    public String getPrice(@RequestParam String custFullName)
    {
        String customerRoomType = bookingService.getByCustFullName(custFullName).getRoomType();
        String url = "http://price-list-service/get?roomType="+customerRoomType;

        int customerTotalNights= bookingService.getByCustFullName(custFullName).getTotalNights();


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

        int finalPrice = customerTotalNights * price;

        return "$ "+price+"/Night" + " | Total price = $"+finalPrice ;

    }

    @RequestMapping("/create")
    public  String create(@RequestParam String custFullName, @RequestParam String custPhone,
                          @RequestParam String custUserID, @RequestParam int totalNights,
                          @RequestParam String roomType)
    {
        String url = "http://room-manager-service/get?roomType="+roomType;
        String str = restTemplate.getForObject(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        String customerRoomName=null;
        try {
            Set<Room> r= mapper.readValue(str,  new TypeReference<Set<Room>>() {});
            int availableCount = 0;

            for(int i=0;i<r.size();i++){
                if(((Room)r.toArray()[i]).getAvailability().equals("1")){
                    String resUrl = "http://room-manager-service/update?roomName="
                            +((Room)r.toArray()[i]).getRoomName()
                            +"&roomType="+((Room)r.toArray()[i]).getRoomType()
                            +"&availability="+"0";
                    customerRoomName=((Room)r.toArray()[i]).getRoomName();
                    //booking added this room not availabale for other customer
                    restTemplate.postForObject(resUrl, "", String.class);
                    availableCount++;
                    break;
                }
            }
            if(availableCount==0){
                return roomType + " room type is currently not available.";
            }
            //System.out.println(price);

        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Booking booking = bookingService.create(custFullName,custPhone,custUserID,totalNights,roomType,customerRoomName);

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

    @RequestMapping("/delete")
    public String delete(@RequestParam String custFullName)
    {

        Booking booking = bookingService.getByCustFullName(custFullName);

        String availUrl = "http://room-manager-service/update?roomName="
                +booking.getRoomName()
                +"&roomType="+booking.getRoomType()
                +"&availability="+"1";
        //Customer leaved this room and this room is available now.
        restTemplate.postForObject(availUrl, "", String.class);
        bookingService.delete(custFullName);
        return custFullName +"'s booking is deleted and room "
                +booking.getRoomName() + " is available now.";
    }

    @RequestMapping("/deleteAll")
    public String deleteAll(){
        bookingService.deleteAll();
        String availUrl = "http://room-manager-service/makeAvailableAll";
        //If all booking are deleted, all rooms have to be available
        restTemplate.postForObject(availUrl, "", String.class);
        return "All booking details deleted and all rooms are available now.";
    }
}
