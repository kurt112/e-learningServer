package com.thesis.ELearning.controller.AdminRest;

import com.thesis.ELearning.entity.*;
import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.service.serviceImplementation.*;
import com.thesis.ELearning.utils.disable.StudentTeacherActivity;
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
    private final StudentTeacherActivity studentTeacherActivity;


    @Autowired
    public RoomClassesAdmin(RoomShiftClassesService roomShiftClassesService, RoomShiftService roomShiftService, SubjectService subjectService, TeacherService teacherService, StudentService studentService, StudentTeacherActivity studentTeacherActivity) {
        this.roomShiftClassesService = roomShiftClassesService;
        this.roomShiftService = roomShiftService;
        this.subjectService = subjectService;
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.studentTeacherActivity = studentTeacherActivity;
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

        RoomShiftClass roomShiftClasses = roomShiftClassesService.findById(id);

        if(roomShiftClasses == null){
            roomShiftClasses = new RoomShiftClass();
            roomShiftClasses.setId(id);
            roomShiftClasses.setStatus(1);
            roomShiftClasses.setCreatedAt(new Date());
        }

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
        Set<Student> students = new HashSet<>();

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

    @PostMapping("/off")
    public ResponseEntity<Response<?>> setRoomClassOff(@RequestParam("id") String id) {

        RoomShiftClass RoomClass = roomShiftClassesService.findById(id);

        RoomClass.setStatus(0);

        roomShiftClassesService.save(RoomClass);

        for(Student student: RoomClass.getStudents()){
            studentTeacherActivity.DisableStudentAssignment(student.getAssignments());
            studentTeacherActivity.DisableStudentExam(student.getExams());
            studentTeacherActivity.DisableStudentQuiz(student.getQuizzes());
        }

        return new ResponseEntity<>(
                new Response<>("RoomShiftClass On", "RoomShiftClass On"),
                HttpStatus.OK
        );
    }

    @PostMapping("/on")
    public ResponseEntity<Response<?>> setRoomClassOn(@RequestParam("id") String id) {

        RoomShiftClass RoomClass  = roomShiftClassesService.findById(id);

        RoomClass.setStatus(1);
        roomShiftClassesService.save(RoomClass);

        for(Student student: RoomClass.getStudents()){
            if(student.getAssignments() != null || student.getAssignments().size()!=0)
                studentTeacherActivity.EnableStudentAssignment(student.getAssignments());
            if(student.getExams() != null || student.getExams().size()!=0)
                studentTeacherActivity.EnableStudentExam(student.getExams());
            if(student.getQuizzes() != null || student.getQuizzes().size()!=0)
                studentTeacherActivity.EnableStudentQuiz(student.getQuizzes());
        }

        return new ResponseEntity<>(
                new Response<>("RoomShiftClass Off", "RoomShiftClass Off"),
                HttpStatus.OK
        );
    }


}
