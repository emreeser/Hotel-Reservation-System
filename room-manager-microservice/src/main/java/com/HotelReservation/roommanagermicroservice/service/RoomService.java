package com.HotelReservation.roommanagermicroservice.service;

import com.HotelReservation.roommanagermicroservice.model.Room;
import com.HotelReservation.roommanagermicroservice.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    //create a Room
    public Room create(String roomName, String roomType)
    {
        return roomRepository.save(new Room(roomName, roomType,"1"));
    }

    //Retrieve the Room details
    public List<Room> getAll()
    {
        return roomRepository.findAll();
    }

    public List<Room> getByRoomType(String roomType)
    {
        return roomRepository.findByRoomType(roomType);
    }

    //update Room Details
    public Room update(String roomName, String roomType, String availability)
    {
        Room r = roomRepository.findByRoomName(roomName);
        r.setRoomType(roomType);
        r.setAvailability(availability);
        return roomRepository.save(r);
    }
    public List<Room> makeAvailableAll()
    {
        List<Room> rooms = roomRepository.findAll();
        for(Room room:rooms){
            room.setAvailability("1");
            roomRepository.save(room);
        }
        return roomRepository.findAll();
    }

    //Delete All Room Details
    public void deleteAlll()
    {
        roomRepository.deleteAll();
    }

    public void delete(String roomName)
    {
        Room r = roomRepository.findByRoomName(roomName);
        roomRepository.delete(r);
    }
}
