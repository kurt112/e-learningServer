package com.thesis.ELearning.controller.AdminRest;

import com.fasterxml.uuid.Generators;
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
@RequestMapping("/admin")
public class RoomShiftAdmin {

    private final RoomService roomService;
    private final RoomShiftService roomShiftService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final CurriculumService curriculumService;
    private final RoomShiftClassesService roomShiftClassesService;
    private final StudentTeacherActivity studentTeacherActivity;


    @Autowired
    public RoomShiftAdmin(RoomService roomService, RoomShiftService roomShiftService, StudentService studentService, TeacherService teacherService, CurriculumService curriculumService, RoomShiftClassesService roomShiftClassesService, StudentTeacherActivity studentTeacherActivity) {
        this.roomService = roomService;
        this.roomShiftService = roomShiftService;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.curriculumService = curriculumService;
        this.roomShiftClassesService = roomShiftClassesService;
        this.studentTeacherActivity = studentTeacherActivity;
    }

    @PostMapping("/register-roomShift")
    public ResponseEntity<Response<?>> roomShift(
            @RequestParam("id") String id,
            @RequestParam("room-id") String roomid,
            @RequestParam("room-shiftID") String shiftName,
            @RequestParam("shiftID-grade") String shiftGrade,
            @RequestParam("shiftID-section") String shiftSection,
            @RequestParam("shiftID-timeStart") String timeStart,
            @RequestParam("shiftID-timeEnd") String timeEnd,
            @RequestParam("teacher-id") String teacherId,
            @RequestParam("curriculum-code") String curriculumCode
    ) {


        Curriculum curriculum = curriculumService.findById(curriculumCode);
        Room room = roomService.findById(roomid);
        Teacher teacher = teacherService.findById(teacherId);

        RoomShift roomShift = roomShiftService.findById(id);

        // pointer for subject in roomshift
        HashMap<Integer,Integer> hashMap = new HashMap<>();

        // when updating new RoomShift
        if(roomShift !=null){

            if(!roomShift.getCurriculum().getCode().equals(curriculum.getCode())){

                for(RoomShiftClass classes: roomShift.getRoomShiftClasses()){
                    roomShiftClassesService.deleteRoomShiftClass(classes.getId());
                    System.out.println(roomShiftClassesService.findById(classes.getId()));
                }

                roomShift.setRoomShiftClasses(new HashSet<>());
                roomShift.setCurriculum(curriculum);
            }

            roomShift.setRoomShiftName(shiftName);
            roomShift.setGrade(shiftGrade);
            roomShift.setSection(shiftSection);
            roomShift.setTimeStart(timeStart);
            roomShift.setTimeEnd(timeEnd);
            roomShift.setRoom(room);
            roomShift.setTeacher(teacher);

        }
        else{
            // creating new roomShift
            roomShift = new RoomShift(id, shiftGrade, shiftSection, timeStart, timeEnd, shiftName, room, teacher,curriculum);
            roomShift.setRoomShiftClasses(new HashSet<>());
            roomShift.setStatus(1);
        }

        roomShiftService.save(roomShift);

        // iterating all class in roomshift
        for(RoomShiftClass classes: roomShift.getRoomShiftClasses()){
            int subjectId =classes.getSubject().getId();
            hashMap.put(subjectId,subjectId);
        }


        // Creating A Class For every subject in roomshift
        for(Subject subject: curriculum.getSubjects()){

            // if the roomshift has a already class we will skip it
            if(hashMap.get(subject.getId()) != null) continue;

            // generating for new class
            String shortUUID = shiftGrade+Generators.randomBasedGenerator().generate().toString().substring(0,7)+shiftSection;
            RoomShiftClass roomShiftClass = new RoomShiftClass(shortUUID,roomShift,subject,new Date(),new Date(),1);
            roomShiftClass.setStudents(roomShift.getStudents());
            roomShiftClassesService.save(roomShiftClass);
        }

        return new ResponseEntity<>(
                new Response<>("Register RoomShift Success", "Success"),
                HttpStatus.OK
        );
    }

    @PostMapping("/room-shift/add/student")
    public ResponseEntity<?> addStudentInRoomShift(@RequestBody HashMap<Object, Object> hashMap){

        List<String> student_id = (List<String>) hashMap.get("students");
        String roomShiftId = hashMap.get("shiftID").toString();

        RoomShift roomShift = roomShiftService.findById(roomShiftId);
        Set<Student> students = new HashSet<>();

        // adding student in room shift
        for(String id: student_id){
            students.add(studentService.getStudentById(id));
        }

        System.out.println(students.size());

        roomShift.setStudents(students);

        roomShiftService.save(roomShift);

        for(RoomShiftClass roomShiftClass: roomShift.getRoomShiftClasses()){
            roomShiftClass.setStudents(students);
            roomShiftClassesService.save(roomShiftClass);
        }




        return new ResponseEntity<>(
                new Response<>("Register Student Success", "Success"),
                HttpStatus.OK
        );
    }


    @DeleteMapping("/delete/roomShift")
    public ResponseEntity<Response<?>> deleteRoomShift(@RequestParam("id") String id) {

        RoomShift roomShift = roomShiftService.findById(id);

        System.out.println(id);

        if(roomShift == null){
            return new ResponseEntity<>(
                    new Response<>("RoomShift Id Is Not Existing", null),
                    HttpStatus.BAD_REQUEST
            );
        }

        roomShiftService.deleteById(id);

        return new ResponseEntity<>(
                new Response<>("Delete RoomShift Success", "RoomShift Delete Success"),
                HttpStatus.OK
        );
    }

    @PostMapping("/off/roomShift")
    public ResponseEntity<Response<?>> setRoomShiftOff(@RequestParam("id") String id) {

        RoomShift roomShift = roomShiftService.findById(id);

        for(RoomShiftClass classes: roomShift.getRoomShiftClasses()){
            classes.setStatus(0);
            roomShiftClassesService.save(classes);

            for(Student student: classes.getStudents()){
                if(student.getAssignments() != null || student.getAssignments().size()!=0)
                    studentTeacherActivity.EnableStudentAssignment(student.getAssignments());
                if(student.getExams() != null || student.getExams().size()!=0)
                    studentTeacherActivity.EnableStudentExam(student.getExams());
                if(student.getQuizzes() != null || student.getQuizzes().size()!=0)
                    studentTeacherActivity.EnableStudentQuiz(student.getQuizzes());
            }
        }

        roomShift.setStatus(0);
        roomShiftService.save(roomShift);

        return new ResponseEntity<>(
                new Response<>("RoomShift On", "RoomShift On"),
                HttpStatus.OK
        );
    }

    @PostMapping("/on/roomShift")
    public ResponseEntity<Response<?>> setRoomShiftOn(@RequestParam("id") String id) {

        RoomShift roomShift  = roomShiftService.findById(id);
        roomShift.setStatus(1);
        roomShiftService.save(roomShift);

        for(RoomShiftClass classes: roomShift.getRoomShiftClasses()){
            classes.setStatus(1);
            roomShiftClassesService.save(classes);

            for(Student student: classes.getStudents()){
                studentTeacherActivity.EnableStudentAssignment(student.getAssignments());
                studentTeacherActivity.EnableStudentExam(student.getExams());
                studentTeacherActivity.EnableStudentQuiz(student.getQuizzes());
            }
        }


        return new ResponseEntity<>(
                new Response<>("RoomShift Off", "RoomShift Off"),
                HttpStatus.OK
        );
    }
}
