package com.thesis.ELearning.controller.AdminRest;

import com.thesis.ELearning.entity.*;
import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.entity.PostEntity.Post_RoomShiftClasses;
import com.thesis.ELearning.service.serviceImplementation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Response<Post_RoomShiftClasses>> classRegister(
            @RequestParam("id") String id,
            @RequestParam("roomShift-id") String roomShiftId,
            @RequestParam("subject-id") String subjectId,
            @RequestParam("time-start") String timeStart,
            @RequestParam("time-end") String timeEnd,
            @RequestParam("day") String day,
            @RequestParam("teacher-id") String teacherId) {

        Teacher teacher = teacherService.findById(teacherId);

        RoomShift roomShift = roomShiftService.findById(roomShiftId);
        Subject subject = subjectService.findById(subjectId);
        RoomShiftClass roomShiftClasses = new RoomShiftClass();

        roomShiftClasses.setId(id);

        // setter
        roomShiftClasses.setDay(day);
        roomShiftClasses.setEndTime(timeEnd);
        roomShiftClasses.setStartTime(timeStart);
        roomShiftClasses.setTeacher(teacher);
        roomShiftClasses.setRoomShift(roomShift);
        roomShiftClasses.setSubject(subject);

        if (roomShiftClasses.getStudents() == null) roomShiftClasses.setStudents(new HashSet<>());


        for (Student student : roomShift.getStudents()) {
            roomShiftClasses.getStudents().add(student);
        }

        roomShiftClassesService.save(roomShiftClasses);
        Post_RoomShiftClasses post_roomShiftClasses = new Post_RoomShiftClasses(roomShift.getRoom().getId(), subject.getSubjectCode(), roomShiftClasses.getId(), roomShift.getRoom().getRoomName(), roomShift.getGrade(), roomShift.getSection(), subject.getSubjectName(), teacher.getUser().getFirstName() + " " + teacher.getUser().getLastName(), day, timeStart, timeEnd);

        return new ResponseEntity<>(
                new Response<>("Register Class Success", post_roomShiftClasses),
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
