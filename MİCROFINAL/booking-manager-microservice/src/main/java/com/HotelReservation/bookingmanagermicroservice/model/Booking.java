package com.HotelReservation.bookingmanagermicroservice.model;

import com.HotelReservation.bookingmanagermicroservice.controller.BookingController;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Booking {

    @Id
    String id;

    String custFullName;
    String custPhone;
    String custUserID;

    int totalNights;

    public static int nights;
    Long totalPrice;

    public Booking(String custFullName, String custPhone, String custUserID, int totalNights) {
        //super();
        this.custFullName = custFullName;
        this.custPhone = custPhone;
        this.custUserID = custUserID;
        this.totalNights = totalNights;
        this.nights = totalNights;
    }

    BookingController b = new BookingController();

    public String getCustFullName() {
        return custFullName;
    }

    public void setCustFullName(String custFullName) {
        this.custFullName = custFullName;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustUserID() {
        return custUserID;
    }

    public void setCustUserId(String custUserId) {
        this.custUserID = custUserId;
    }

    public int getTotalNights() {
        return totalNights;
    }

    public void setTotalNights(int totalNights) {
        this.totalNights = totalNights;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = (long) b.totprice * totalNights;
    }

    @Override
    public String toString() {
        return "Booking [custFullName=" + custFullName  + ", custPhone=" + custPhone
                + ", custUserId=" + custUserID + ", totalNights=" + totalNights + "]";
    }

}