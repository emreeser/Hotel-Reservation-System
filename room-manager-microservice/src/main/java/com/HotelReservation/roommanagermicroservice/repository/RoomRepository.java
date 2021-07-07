package com.HotelReservation.roommanagermicroservice.repository;

import com.HotelReservation.roommanagermicroservice.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends MongoRepository<Room,String> {

    public Room findByRoomName(String roomName);

    public List<Room> findByRoomType(String roomType);
}
