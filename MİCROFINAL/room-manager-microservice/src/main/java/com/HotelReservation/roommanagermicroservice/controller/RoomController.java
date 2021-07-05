package com.HotelReservation.roommanagermicroservice.controller;

import com.HotelReservation.roommanagermicroservice.model.Room;
import com.HotelReservation.roommanagermicroservice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomController {

    @Autowired
    private RoomService roomService;

    @RequestMapping("/create")
    public String create(@RequestParam String roomName, @RequestParam String roomType)
    {
        Room r = roomService.create(roomName, roomType);
        return r.toString();

    }

    @RequestMapping("/get")
    public Room getRoom(@RequestParam String roomName)
    {
        return roomService.getByRoomName(roomName);
    }

    @RequestMapping("/getAll")
    public List<Room> getAll()
    {
        return roomService.getAll();
    }

    @RequestMapping("/update")
    public String update(@RequestParam String roomName, @RequestParam String roomType)
    {
        Room r = roomService.update(roomName, roomType);
        return r.toString();
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam String roomName)
    {
        roomService.delete(roomName);
        return roomName+" is deleted";
    }

    @RequestMapping("/deleteAll")
    public String deleteAll()
    {
        roomService.deleteAlll();
        return "All Rooms details deleted";
    }
}
