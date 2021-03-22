package com.thesis.ELearning.controller.AdminRest;

import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.entity.PostEntity.Post_RoomShift;
import com.thesis.ELearning.entity.Room;
import com.thesis.ELearning.entity.RoomShift;
import com.thesis.ELearning.entity.RoomShiftClass;
import com.thesis.ELearning.entity.Student;
import com.thesis.ELearning.repository.RoomShiftClassesRepository;
import com.thesis.ELearning.service.serviceImplementation.RoomService;
import com.thesis.ELearning.service.serviceImplementation.RoomShiftService;
import com.thesis.ELearning.service.serviceImplementation.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class RoomShiftAdmin {

    final private RoomService roomService;
    final private RoomShiftService roomShiftService;
    final private StudentService studentService;
    final private RoomShiftClassesRepository roomShiftClassesRepository;
    private List<Student> currentStudentList = new ArrayList<>();

    @Autowired
    public RoomShiftAdmin(RoomService roomService, RoomShiftService roomShiftService, StudentService studentService, RoomShiftClassesRepository roomShiftClassesRepository) {
        this.roomService = roomService;
        this.roomShiftService = roomShiftService;
        this.studentService = studentService;
        this.roomShiftClassesRepository = roomShiftClassesRepository;
    }


    @PostMapping("/register-roomShift")
    public ResponseEntity<Response<Post_RoomShift>> roomShift(
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
        Post_RoomShift post_roomShift = new Post_RoomShift(roomShift.getId(),room.getRoomName(),shiftName,shiftGrade,shiftSection,timeStart,timeEnd);

        return new ResponseEntity<>(
                new Response<>("Register RoomShift Success", post_roomShift),
                HttpStatus.OK
        );
    }

    @PostMapping("/add-studentRoomShift")
    public ResponseEntity<?> addStudentRoomShift(@RequestParam("student-id") String studentId){

        Student student = studentService.findById(studentId);
        System.out.println(studentId);
        currentStudentList.add(student);
        return new ResponseEntity<>(
                new Response<>("Register Student Success", "Success"),
                HttpStatus.OK
        );
    }

    @PostMapping("/doneAddingStudentInRoomShift")
    public ResponseEntity<?> doneAdd(@RequestParam("roomShiftID") String id){

        RoomShift roomShift = roomShiftService.findById(id);
        for(RoomShiftClass roomShiftClass: roomShift.getRoomShiftClasses()){

            roomShiftClass.setStudents(new HashSet<>(currentStudentList));

            roomShiftClassesRepository.save(roomShiftClass);
        }


        roomShift.setStudents(currentStudentList);
        roomShiftService.save(roomShift);
        currentStudentList = new ArrayList<>();


        return new ResponseEntity<>(
                new Response<>("Register Student Success", "Success"),
                HttpStatus.OK
        );
    }
}
