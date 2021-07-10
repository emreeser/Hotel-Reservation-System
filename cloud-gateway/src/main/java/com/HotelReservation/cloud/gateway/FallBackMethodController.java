package com.HotelReservation.cloud.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

    @GetMapping("/priceServiceFallBack")
    public String priceServiceFallBackMethod(){
        return "Price service is taking longer than expected."+
                " Please try again later";
    }

    @GetMapping("/bookingServiceFallBack")
    public String bookingServiceFallBackMethod(){
        return "Booking service is taking longer than expected."+
                " Please try again later";
    }

    @GetMapping("/roomServiceFallBack")
    public String roomServiceFallBackMethod(){
        return "Room service is taking longer than expected."+
                " Please try again later";
    }
}
