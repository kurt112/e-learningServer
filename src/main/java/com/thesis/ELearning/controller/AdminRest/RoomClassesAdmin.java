package com.thesis.ELearning.controller.AdminRest;

import com.fasterxml.uuid.Generators;
import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.entity.PostEntity.Post_RoomShiftClasses;
import com.thesis.ELearning.entity.RoomShiftClass;
import com.thesis.ELearning.entity.RoomShift;
import com.thesis.ELearning.entity.Subject;
import com.thesis.ELearning.entity.Teacher;
import com.thesis.ELearning.service.serviceImplementation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/admin/classes")
public class RoomClassesAdmin {

    private final RoomShiftClassesService roomShiftClassesService;
//    private final RoomService roomService;
    private final RoomShiftService roomShiftService;
    private final SubjectService subjectService;
    private final TeacherService teacherService;

    @Autowired
    public RoomClassesAdmin(RoomShiftClassesService roomShiftClassesService, RoomShiftService roomShiftService, SubjectService subjectService, TeacherService teacherService) {
        this.roomShiftClassesService = roomShiftClassesService;
//        this.roomService = roomService;
        this.roomShiftService = roomShiftService;
        this.subjectService = subjectService;
        this.teacherService = teacherService;
    }

    @PostMapping("/register")
    public ResponseEntity<Response<Post_RoomShiftClasses>> classRegister(
                                                               @RequestParam("roomShift-id") String roomShiftId,
                                                               @RequestParam("subject-id") String subjectId,
                                                               @RequestParam("time-start") String timeStart,
                                                               @RequestParam("time-end")String timeEnd,
                                                               @RequestParam("day") String day,
                                                               @RequestParam("teacher-id") String teacherId){

        Teacher teacher = teacherService.findById(teacherId);

        RoomShift roomShift = roomShiftService.findById(roomShiftId);
        Subject subject =  subjectService.findById(subjectId);
        RoomShiftClass roomShiftClasses = new RoomShiftClass();
        UUID uuidGenerator = Generators.randomBasedGenerator().generate();

        roomShiftClasses.setId(uuidGenerator.toString());

        // setter
        roomShiftClasses.setDay(day);
        roomShiftClasses.setEndTime(timeEnd);
        roomShiftClasses.setStartTime(timeStart);
        roomShiftClasses.setTeacher(teacher);
        roomShiftClasses.setRoomShift(roomShift);
        roomShiftClasses.setSubject(subject);

        roomShiftClassesService.save(roomShiftClasses);
        Post_RoomShiftClasses post_roomShiftClasses = new Post_RoomShiftClasses(roomShift.getRoom().getId(),subject.getSubjectCode(),roomShiftClasses.getId(),roomShift.getRoom().getRoomName(),roomShift.getGrade(),roomShift.getSection(),subject.getSubjectName(),teacher.getUser().getFirstName() + " " + teacher.getUser().getLastName(),day,timeStart,timeEnd);

        return new ResponseEntity<>(
                new Response<>("Register Class Success", post_roomShiftClasses),
                HttpStatus.OK
        );
    }


}
