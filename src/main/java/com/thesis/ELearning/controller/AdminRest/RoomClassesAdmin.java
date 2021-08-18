package com.thesis.ELearning.controller.AdminRest;

import com.thesis.ELearning.entity.*;
import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.service.serviceImplementation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/admin/classes")
public class RoomClassesAdmin {

    private final RoomShiftClassesService roomShiftClassesService;
    private final RoomShiftService roomShiftService;
    private final SubjectService subjectService;
    private final TeacherService teacherService;
    private final StudentService studentService;

    @Autowired
    public RoomClassesAdmin(RoomShiftClassesService roomShiftClassesService, RoomShiftService roomShiftService, SubjectService subjectService, TeacherService teacherService, StudentService studentService) {
        this.roomShiftClassesService = roomShiftClassesService;
        this.roomShiftService = roomShiftService;
        this.subjectService = subjectService;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @PostMapping("/register")
    public ResponseEntity<Response<?>> classRegister(
            @RequestParam("id") String id,
            @RequestParam("roomShift-id") String roomShiftId,
            @RequestParam("subject-id") int subjectId,
            @RequestParam("time-start") String timeStart,
            @RequestParam("time-end") String timeEnd,
            @RequestParam("day") String day,
            @RequestParam("teacher-id") String teacherId

    ) {

        Teacher teacher = teacherService.findById(teacherId);
        RoomShift roomShift = roomShiftService.findById(roomShiftId);
        Subject subject = subjectService.findByIdIndex(subjectId);
        RoomShiftClass existing = roomShiftClassesService.findById(id);
        RoomShiftClass roomShiftClasses = existing == null ? new RoomShiftClass():existing;

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

        if (roomShiftClasses.getStudents() == null) roomShiftClasses.setStudents(new ArrayList<>());

        for (Student student : roomShift.getStudents()) {
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

    @PostMapping("/add/student")
    public ResponseEntity<?> addStudentInRoomClass(@RequestBody HashMap<Object, Object> hashMap){

        List<String> student_id = (List<String>) hashMap.get("students");
        String classID = hashMap.get("classID").toString();

        RoomShiftClass RoomClass = roomShiftClassesService.findById(classID);
        List<Student> students = new ArrayList<>();

        // adding student in room shift
        for(String id: student_id){
            System.out.println("Adding ");
            students.add(studentService.getStudentById(id));
        }

        RoomClass.setStudents(students);


        roomShiftClassesService.save(RoomClass);

        return new ResponseEntity<>(
                new Response<>("Register Student Success", "Success"),
                HttpStatus.OK
        );
    }


}
