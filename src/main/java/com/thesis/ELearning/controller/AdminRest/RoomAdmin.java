package com.thesis.ELearning.controller.AdminRest;

import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.entity.Room;
import com.thesis.ELearning.entity.RoomShift;
import com.thesis.ELearning.entity.RoomShiftClass;
import com.thesis.ELearning.service.serviceImplementation.RoomService;
import com.thesis.ELearning.service.serviceImplementation.RoomShiftClassesService;
import com.thesis.ELearning.service.serviceImplementation.RoomShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class RoomAdmin {

    private final RoomService roomService;
    private final RoomShiftService roomShiftService;
    private final RoomShiftClassesService roomShiftClassesService;

    @Autowired
    public RoomAdmin(RoomService roomService, RoomShiftService roomShiftService, RoomShiftClassesService roomShiftClassesService) {
        this.roomService = roomService;
        this.roomShiftService = roomShiftService;
        this.roomShiftClassesService = roomShiftClassesService;
    }


    @PostMapping("/room-register")
    public ResponseEntity<Response<Room>> AddRoom(@RequestBody Room room) {
        room.setStatus(1);
        roomService.save(room);
        return new ResponseEntity<>(
                new Response<>("Register Room Success", room),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/room")
    public ResponseEntity<Response<?>> deleteRoom(@RequestParam("id") String id) {

        Room room = roomService.findById(id);

        if (room == null) {
            return new ResponseEntity<>(
                    new Response<>("Room Id Is Not Existing", "Room Delete Not Success"),
                    HttpStatus.BAD_REQUEST
            );
        }

        roomService.deleteById(id);

        return new ResponseEntity<>(
                new Response<>("Delete Room Success", "Room Delete Success"),
                HttpStatus.OK
        );
    }

    @PostMapping("/off/room")
    public ResponseEntity<Response<?>> setRoomOff(@RequestParam("id") String id) {

        Room room = roomService.findById(id);

        for (RoomShift roomShift : room.getRoomShifts()) {
            roomShift.setStatus(0);
            roomShiftService.save(roomShift);

            for (RoomShiftClass classes : roomShift.getRoomShiftClasses()) {
                classes.setStatus(0);
                roomShiftClassesService.save(classes);
            }

        }

        room.setStatus(0);


        roomService.save(room);

        return new ResponseEntity<>(
                new Response<>("Room On", "Room On"),
                HttpStatus.OK
        );
    }

    @PostMapping("/on/room")
    public ResponseEntity<Response<?>> setRoomOn(@RequestParam("id") String id) {

        Room room = roomService.findById(id);

        for (RoomShift roomShift : room.getRoomShifts()) {
            roomShift.setStatus(1);
            roomShiftService.save(roomShift);

            for (RoomShiftClass classes : roomShift.getRoomShiftClasses()) {
                classes.setStatus(1);
                roomShiftClassesService.save(classes);
            }

        }

        room.setStatus(1);
        roomService.save(room);

        return new ResponseEntity<>(
                new Response<>("Room Off", "Room Off"),
                HttpStatus.OK
        );
    }
}
