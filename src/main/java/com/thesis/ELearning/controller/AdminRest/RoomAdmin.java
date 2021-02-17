package com.thesis.ELearning.controller.AdminRest;

import com.fasterxml.uuid.Generators;
import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.entity.Room;
import com.thesis.ELearning.service.serviceImplementation.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class RoomAdmin {

    private final RoomService roomService;

    @Autowired
    public RoomAdmin(RoomService roomService) {
        this.roomService = roomService;
    }


    @PostMapping("/room-register")
    public ResponseEntity<Response<Room>> AddRoom(@RequestBody Room room){
        UUID uuidGenerator = Generators.randomBasedGenerator().generate();
        System.out.println(room.toString());
        room.setId(uuidGenerator.toString());
        roomService.save(room);
        return new ResponseEntity<>(
                new Response<>("Register Room Success", room),
                HttpStatus.OK
        );
    }
}
