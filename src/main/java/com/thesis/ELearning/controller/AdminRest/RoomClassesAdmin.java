package com.thesis.ELearning.controller.AdminRest;

import com.fasterxml.uuid.Generators;
import com.thesis.ELearning.entity.API.PagesContent;
import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.entity.RoomClass;
import com.thesis.ELearning.entity.RoomShift;
import com.thesis.ELearning.entity.Subject;
import com.thesis.ELearning.entity.Teacher;
import com.thesis.ELearning.service.serviceImplementation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/admin/classes")
public class RoomClassesAdmin {

    private final RoomClassesService roomClassesService;
//    private final RoomService roomService;
    private final RoomShiftService roomShiftService;
    private final SubjectService subjectService;
    private final TeacherService teacherService;

    @Autowired
    public RoomClassesAdmin(RoomClassesService roomClassesService,RoomShiftService roomShiftService, SubjectService subjectService, TeacherService teacherService) {
        this.roomClassesService = roomClassesService;
//        this.roomService = roomService;
        this.roomShiftService = roomShiftService;
        this.subjectService = subjectService;
        this.teacherService = teacherService;
    }

    @PostMapping("/register")
    public ResponseEntity<Response<RoomClass>> classRegister(
                                                               @RequestParam("roomShift-id") String roomShiftId,
                                                               @RequestParam("subject-id") String subjectId,
                                                               @RequestParam("time-start") String timeStart,
                                                               @RequestParam("time-end")String timeEnd,
                                                               @RequestParam("day") String day,
                                                               @RequestParam("teacher-id") String teacherId){

        Teacher teacher = teacherService.findById(teacherId);

        RoomShift roomShift = roomShiftService.findById(roomShiftId);
        Subject subject =  subjectService.findById(subjectId);
        RoomClass roomClasses = new RoomClass();
        UUID uuidGenerator = Generators.randomBasedGenerator().generate();

        roomClasses.setId(uuidGenerator.toString());

        // setter
        roomClasses.setDay(day);
        roomClasses.setEndTime(timeEnd);
        roomClasses.setStartTime(timeStart);
        roomClasses.setTeacher(teacher);
        roomClasses.setRoomShift(roomShift);
        roomClasses.setSubject(subject);

        roomClassesService.save(roomClasses);

        return new ResponseEntity<>(
                new Response<>("Register Class Success", roomClasses),
                HttpStatus.OK
        );
    }


}
