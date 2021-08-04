package com.thesis.ELearning.controller.AdminRest;

import com.thesis.ELearning.entity.*;
import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.service.serviceImplementation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;


@RestController
@RequestMapping("/admin/classes")
public class RoomClassesAdmin {

    private final RoomShiftClassesService roomShiftClassesService;
    private final RoomShiftService roomShiftService;
    private final SubjectService subjectService;
    private final TeacherService teacherService;

    @Autowired
    public RoomClassesAdmin(RoomShiftClassesService roomShiftClassesService, RoomShiftService roomShiftService, SubjectService subjectService, TeacherService teacherService) {
        this.roomShiftClassesService = roomShiftClassesService;
        this.roomShiftService = roomShiftService;
        this.subjectService = subjectService;
        this.teacherService = teacherService;
    }

    @PostMapping("/register")
    public ResponseEntity<Response<?>> classRegister(
            @RequestParam("id") String id,
            @RequestParam("roomShift-id") String roomShiftId,
            @RequestParam("subject-id") int subjectId,
            @RequestParam("time-start") String timeStart,
            @RequestParam("time-end") String timeEnd,
            @RequestParam("day") String day,
            @RequestParam("teacher-id") String teacherId) {


        Teacher teacher = teacherService.findById(teacherId);
        RoomShift roomShift = roomShiftService.findById(roomShiftId);
        Subject subject = subjectService.findByIdIndex(subjectId);
        RoomShiftClass roomShiftClasses = new RoomShiftClass();

        roomShiftClasses.setId(id);

        // setter
        roomShiftClasses.setDay(day);
        roomShiftClasses.setEndTime(timeEnd);
        roomShiftClasses.setStartTime(timeStart);
        roomShiftClasses.setTeacher(teacher);
        roomShiftClasses.setRoomShift(roomShift);
        roomShiftClasses.setSubject(subject);
        roomShiftClasses.setCreatedAt(new Date());
        roomShiftClasses.setStatus(1);

        System.out.println("saving data");

        if (roomShiftClasses.getStudents() == null) roomShiftClasses.setStudents(new ArrayList<>());

        for (Student student : roomShift.getStudents()) {
            System.out.println(student.getUser());
            roomShiftClasses.getStudents().add(student);
        }

        roomShiftClassesService.save(roomShiftClasses);

        return new ResponseEntity<>(
                new Response<>("Register Class Success", "Success"),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/class")
    public ResponseEntity<Response<?>> deleteClasses(@RequestParam("id") String id) {
        System.out.println("the id " + id);
        RoomShiftClass classes = roomShiftClassesService.findById(id);


        if (classes == null) {
            return new ResponseEntity<>(
                    new Response<>("Class Id Is Not Existing", null),
                    HttpStatus.BAD_REQUEST
            );
        }

        roomShiftClassesService.deleteById(id);

        return new ResponseEntity<>(
                new Response<>("Delete Class Success", "Class Delete Success"),
                HttpStatus.OK
        );
    }


}
