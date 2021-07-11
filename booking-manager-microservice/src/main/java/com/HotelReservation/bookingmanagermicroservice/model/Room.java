package com.HotelReservation.bookingmanagermicroservice.model;

public class Room {
    String roomName;
    String roomType;
    String availability;

    public Room() {
    }

    public Room(String roomName, String roomType, String availability) {
        this.roomName = roomName;
        this.roomType = roomType;
        this.availability = availability;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Room [" +
                "roomName=" + roomName +
                ", roomType='" + roomType +
                ", availability=" + availability +
                ']';
    }
}
