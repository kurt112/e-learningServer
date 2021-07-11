package com.thesis.ELearning.controller.AdminRest;

import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.entity.Room;
import com.thesis.ELearning.entity.RoomShift;
import com.thesis.ELearning.entity.Student;
import com.thesis.ELearning.entity.Teacher;
import com.thesis.ELearning.service.serviceImplementation.RoomService;
import com.thesis.ELearning.service.serviceImplementation.RoomShiftService;
import com.thesis.ELearning.service.serviceImplementation.StudentService;
import com.thesis.ELearning.service.serviceImplementation.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class RoomShiftAdmin {

    private final RoomService roomService;
    private final RoomShiftService roomShiftService;
    private final StudentService studentService;
    private final TeacherService teacherService;

    @Autowired
    public RoomShiftAdmin(RoomService roomService, RoomShiftService roomShiftService, StudentService studentService, TeacherService teacherService) {
        this.roomService = roomService;
        this.roomShiftService = roomShiftService;
        this.studentService = studentService;
        this.teacherService = teacherService;
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
            @RequestParam("teacher-id") String teacherId
    ) {
        System.out.println(teacherId);
        Room room = roomService.findById(roomid);
        Teacher teacher = teacherService.findById(teacherId);
        RoomShift roomShift = new RoomShift(id, shiftGrade, shiftSection, timeStart, timeEnd, shiftName, room, teacher);
        roomShiftService.save(roomShift);

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
        List<Student> students = new ArrayList<>();
        for(String id: student_id){
            students.add(studentService.getStudentById(id));
        }

        roomShift.setStudents(students);
        roomShiftService.save(roomShift);

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
}
