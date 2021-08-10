package com.thesis.ELearning.controller;

import com.thesis.ELearning.entity.*;
import com.thesis.ELearning.service.serviceImplementation.*;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author Kurt Orioque
 */
@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final StudentClassAttendanceService studentClassAttendanceService;
    private final TeacherClassAttendanceService teacherClassAttendanceService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final RoomShiftClassesService roomShiftClassesService;

    @Autowired
    public AttendanceController(StudentClassAttendanceService studentClassAttendanceService, TeacherClassAttendanceService teacherClassAttendanceService, StudentService studentService, TeacherService teacherService, RoomShiftClassesService roomShiftClassesService) {
        this.studentClassAttendanceService = studentClassAttendanceService;
        this.teacherClassAttendanceService = teacherClassAttendanceService;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.roomShiftClassesService = roomShiftClassesService;
    }


    @PostMapping("/student")
    public ResponseEntity<?> studentAttendance(@RequestParam("id") String id, @RequestParam("class-id") String classId){
        Student student = studentService.getStudentById(id);
        RoomShiftClass roomShiftClass = roomShiftClassesService.findById(classId);
        StudentClassAttendance lastAttendance = studentClassAttendanceService.getLast();

        if(lastAttendance == null){
            studentClassAttendanceService.save(new StudentClassAttendance(-1,student,roomShiftClass,null));
            return ResponseEntity.ok("Student Attendance Good");
        }
        Date date=java.util.Calendar.getInstance().getTime();
        boolean sameDay = DateUtils.isSameDay(date, lastAttendance.getCreatedAt());

        // attendance will add if same day but different class
        if(!sameDay || !lastAttendance.getStudent_class().getId().equals(classId))  studentClassAttendanceService.save(new StudentClassAttendance(-1,student,roomShiftClass,null));

        return ResponseEntity.ok("Student Attendance Good");
    }


    @PostMapping("/teacher")
    public ResponseEntity<?> teacherAttendance(@RequestParam("id") String id, @RequestParam("class-id") String classId) {
        Teacher teacher = teacherService.getTeacherById(id);
        RoomShiftClass roomShiftClass = roomShiftClassesService.findById(classId);
        TeacherClassAttendance lastAttendance = teacherClassAttendanceService.getLast();

        if(lastAttendance == null) {
            teacherClassAttendanceService.save(new TeacherClassAttendance(-1,teacher,roomShiftClass,null));
            return ResponseEntity.ok("Teacher Attendance Good");
        }
        Date date=java.util.Calendar.getInstance().getTime();
        boolean sameDay = DateUtils.isSameDay(date, lastAttendance.getCreatedAt());


        // attendance will add if same day but different class
        if(!sameDay || !lastAttendance.getTeacher_class().getId().equals(classId))
            teacherClassAttendanceService.save(new TeacherClassAttendance(-1,teacher,roomShiftClass,null));


        return ResponseEntity.ok("Teacher Attendance Good");
    }
}
