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
        return roomRepository.save(new Room(roomName, roomType));
    }

    //Retrieve the Room details
    public List<Room> getAll()
    {
        return roomRepository.findAll();
    }

    public Room getByRoomName(String roomName)
    {
        return roomRepository.findByRoomName(roomName);
    }

    //update Room Details
    public Room update(String roomName, String roomType)
    {
        Room r = roomRepository.findByRoomName(roomName);
        r.setRoomType(roomType);
        return roomRepository.save(r);
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
