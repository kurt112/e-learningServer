package com.thesis.ELearning.controller.AdminRest;

import com.thesis.ELearning.entity.API.PagesContent;
import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.entity.Room;
import com.thesis.ELearning.entity.RoomShift;
import com.thesis.ELearning.service.serviceImplementation.RoomService;
import com.thesis.ELearning.service.serviceImplementation.RoomShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class RoomShiftAdmin {
    final private RoomService roomService;
    final private RoomShiftService roomShiftService;

    @Autowired
    public RoomShiftAdmin(RoomService roomService, RoomShiftService roomShiftService) {
        this.roomService = roomService;
        this.roomShiftService = roomShiftService;
    }


    @PostMapping("/register-roomShift")
    public ResponseEntity<Response<RoomShift>> roomShift(
            @RequestParam("room-id") String roomid,
            @RequestParam("room-shiftID") String shiftName,
            @RequestParam("shiftID-grade") String shiftGrade,
            @RequestParam("shiftID-section") String shiftSection,
            @RequestParam("shiftID-timeStart") String timeStart,
            @RequestParam("shiftID-timeEnd") String timeEnd
    ) {

        Room room = roomService.findById(roomid);
        RoomShift roomShift = new RoomShift(0, shiftGrade, shiftSection, timeStart, timeEnd, shiftName, room);
        System.out.println(roomShift.toString());
        roomShiftService.save(roomShift);
        return new ResponseEntity<>(
                new Response<>("Register Student Success", roomShift),
                HttpStatus.OK
        );
    }
}
