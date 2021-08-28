package com.thesis.ELearning.controller;

import com.thesis.ELearning.entity.*;
import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.repository.TeacherRepository;
import com.thesis.ELearning.service.serviceImplementation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherRepository teacherRepository;
    private final TeacherResourceService teacherResourceService;
    private final TeacherAssignmentService teacherAssignmentService;
    private final RoomShiftClassesService roomShiftClassesService;
    private final TeacherLectureService lectureService;
    private final TeacherExamsService teacherExamsService;
    private final TeacherQuizzesService teacherQuizzesService;

    // for sync the todos with teacher
    private final StudentAssignmentService studentAssignmentService;
    private final StudentExamService studentExamService;
    private final StudentQuizService studentQuizService;


    @Autowired
    public TeacherController(TeacherRepository teacherRepository, TeacherResourceService teacherResourceService, TeacherAssignmentService teacherAssignmentService, RoomShiftClassesService roomShiftClassesService, TeacherLectureService lectureService, TeacherExamsService teacherExamsService, TeacherQuizzesService teacherQuizzesService, StudentAssignmentService studentAssignmentService, StudentExamService studentExamService, StudentQuizService studentQuizService) {
        this.teacherRepository = teacherRepository;
        this.teacherResourceService = teacherResourceService;
        this.teacherAssignmentService = teacherAssignmentService;
        this.roomShiftClassesService = roomShiftClassesService;
        this.lectureService = lectureService;
        this.teacherExamsService = teacherExamsService;
        this.teacherQuizzesService = teacherQuizzesService;
        this.studentAssignmentService = studentAssignmentService;
        this.studentExamService = studentExamService;
        this.studentQuizService = studentQuizService;
    }

    @PostMapping("/upload/resource")
    private ResponseEntity<Response<?>> uploadResource(
            @RequestParam("filePath") String filePath,
            @RequestParam("name") String name,
            @RequestParam("type") String type,
            @RequestParam("description") String description,
            @RequestParam("code") String code,
            @RequestParam("email") String email
    ) {
        Teacher teacher = teacherRepository.getTeacherByUserEmail(email);
        TeacherResources teacherResources = new TeacherResources(code, name, filePath, type, description, new Date());
        teacherResources.setStatus(0);
        teacherResources.setTeacher(teacher);
        teacherResourceService.save(teacherResources);

        return new ResponseEntity<>(
                new Response<>("Create Resource Successful", "Resource Upload Success"),
                HttpStatus.OK
        );

    }

    @DeleteMapping("/delete/resource")
    public ResponseEntity<Response<?>> deleteResource(@RequestParam("code") String code, @RequestParam("email") String email) {

        TeacherResources resource = teacherResourceService.FindResourceByTeacherEmail(code, email);

        if (resource == null) {
            return new ResponseEntity<>(
                    new Response<>("Resource Is Not Existing", null),
                    HttpStatus.BAD_REQUEST
            );
        }

        try {
            teacherResourceService.deleteById(code);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new Response<>("This Resources Is Used Can't Delete", null),
                    HttpStatus.BAD_REQUEST
            );
        }


        return new ResponseEntity<>(
                new Response<>("Delete Resource Success", resource.getLocation()),
                HttpStatus.OK
        );
    }

    @GetMapping("/resource/download")
    public ResponseEntity<?> downloadFile(@RequestParam("code") String code) throws IOException {

        TeacherResources teacherResources = teacherResourceService.findById(code);
        File resource_file = new File(teacherResources.getLocation());
        InputStreamResource resource = new InputStreamResource(new FileInputStream(resource_file));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition",
                String.format("attachment; filename=\"%s\"", resource_file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok().headers(headers)
                .contentLength(resource_file.length())
                .contentType(MediaType.parseMediaType("application/txt")).body(resource);
    }

    @DeleteMapping("/assignment/delete")
    public ResponseEntity<Response<?>> deleteAssignment(@RequestParam("code") String code, @RequestParam("email") String email) {

        TeacherAssignment assignment = teacherAssignmentService.getRoomShiftClassAssignment(code, email);

        if (assignment == null) {
            return new ResponseEntity<>(
                    new Response<>("Assignment Code Is Not Existing", null),
                    HttpStatus.BAD_REQUEST
            );
        }

        teacherAssignmentService.deleteById(code);

        return new ResponseEntity<>(
                new Response<>("Delete Assignment Success", code),
                HttpStatus.OK
        );
    }

    @PostMapping("/assignment/create")
    public ResponseEntity<Response<?>> createAssignment(@RequestBody HashMap<Object, Object> hashMap) {
        String classCode = (String) hashMap.get("classCode");
        double highGrade = Double.parseDouble(hashMap.get("highGrade").toString());
        double lowGrade = Double.parseDouble(hashMap.get("lowGrade").toString());
        String deadLine = hashMap.get("deadLine").toString();
        String resourceCode = (String) hashMap.get("resourceCode");
        String code = (String) hashMap.get("code");
        String description = (String) hashMap.get("description");
        int sem = Integer.parseInt(hashMap.get("sem").toString());
        int quarter = Integer.parseInt(hashMap.get("quarter").toString());
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        Date date = null;

        try {
            date = formatter.parse(deadLine);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        RoomShiftClass classes = roomShiftClassesService.findById(classCode);
        TeacherResources teacherResources = teacherResourceService.findById(resourceCode);
        teacherResources.setStatus(1);

        TeacherAssignment assignment =
                new TeacherAssignment(code, highGrade, lowGrade, quarter, sem, description,
                        new Date(), date, teacherResources,
                        classes);

        teacherAssignmentService.save(assignment);


        for (Student student : classes.getStudents()) {
            studentAssignmentService.save(
                    new StudentAssignment(0, -1, student, assignment, new Date())
            );
        }

        teacherResourceService.save(teacherResources);
        return new ResponseEntity<>(
                new Response<>("Assignment Create Success", "Successful"),
                HttpStatus.OK
        );
    }

    @PostMapping("/lecture/create")
    public ResponseEntity<Response<?>> createLecture(@RequestBody HashMap<Object, Object> hashMap) {
        int quarter = Integer.parseInt(hashMap.get("quarter").toString());
        int sem = Integer.parseInt(hashMap.get("sem").toString());
        RoomShiftClass classes = roomShiftClassesService.findById(hashMap.get("classCode").toString());
        String code = hashMap.get("code").toString();
        TeacherResources resources = teacherResourceService.findById(hashMap.get("resourceCode").toString());
        String description = hashMap.get("description").toString();
        resources.setStatus(1);
        lectureService.save(new TeacherLectures(code, description, quarter, sem, resources, classes, new Date(), null));
        teacherResourceService.save(resources);
        return new ResponseEntity<>(
                new Response<>("Lecture  Create Success", "Successful"),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/lecture/delete")
    public ResponseEntity<Response<?>> deleteLecture(@RequestParam("code") String code, @RequestParam("email") String email) {

        TeacherLectures lectures = lectureService.findTeacherLectureByCode(code, email);

        if (lectures == null) {
            return new ResponseEntity<>(
                    new Response<>("Lecture Code Is Not Existing", null),
                    HttpStatus.BAD_REQUEST
            );
        }

        lectureService.deleteById(code);

        if(lectures.getResource().getTeacherLectures().size() ==0){
            lectures.getResource().setStatus(0);
            teacherResourceService.save(lectures.getResource());
        }

        return new ResponseEntity<>(
                new Response<>("Delete Assignment Success", code),
                HttpStatus.OK
        );
    }

    @PostMapping("/exam/create")
    public ResponseEntity<Response<?>> createExam(@RequestBody HashMap<Object, Object> hashMap) {
        String classCode = (String) hashMap.get("classCode");
        double highGrade = Double.parseDouble(hashMap.get("highGrade").toString());
        double lowGrade = Double.parseDouble(hashMap.get("lowGrade").toString());
        String deadLine = hashMap.get("deadLine").toString();
        String resourceCode = (String) hashMap.get("resourceCode");
        String code = (String) hashMap.get("code");
        String description = (String) hashMap.get("description");
        int sem = Integer.parseInt(hashMap.get("sem").toString());
        int quarter = Integer.parseInt(hashMap.get("quarter").toString());
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        Date date = null;
        try {
            date = formatter.parse(deadLine);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        RoomShiftClass classes = roomShiftClassesService.findById(classCode);
        TeacherResources teacherResources = teacherResourceService.findById(resourceCode);

        TeacherExams exams =
                new TeacherExams(code, highGrade, lowGrade, quarter, sem, description,
                        new Date(), date, teacherResources,
                        classes);

        teacherExamsService.save(exams);

        for (Student student : classes.getStudents()) {
            studentExamService.save(
                    new StudentExam(0, -1, student, exams, new Date())
            );
        }

        teacherResources.setStatus(1);
        teacherResourceService.save(teacherResources);


        return new ResponseEntity<>(
                new Response<>("Exam Create Success", "Successful"),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/exam/delete")
    public ResponseEntity<Response<?>> deleteExam(@RequestParam("code") String code, @RequestParam("email") String email) {

        TeacherExams exams = teacherExamsService.findTeacherExamByCode(code, email);

        if (exams == null) {
            return new ResponseEntity<>(
                    new Response<>("Exam Code Is Not Existing", null),
                    HttpStatus.BAD_REQUEST
            );
        }

        teacherExamsService.deleteById(code);

        if(exams.getResource().getTeacherExams().size() ==0){
            exams.getResource().setStatus(0);
            teacherResourceService.save(exams.getResource());
        }

        return new ResponseEntity<>(
                new Response<>("Delete Exam Success", "Success"),
                HttpStatus.OK
        );
    }

    @PostMapping("/quiz/create")
    public ResponseEntity<Response<?>> createQuiz(@RequestBody HashMap<Object, Object> hashMap) {
        String classCode = (String) hashMap.get("classCode");
        double highGrade = Double.parseDouble(hashMap.get("highGrade").toString());
        double lowGrade = Double.parseDouble(hashMap.get("lowGrade").toString());
        String deadLine = hashMap.get("deadLine").toString();
        String resourceCode = (String) hashMap.get("resourceCode");
        String code = (String) hashMap.get("code");
        String description = (String) hashMap.get("description");
        int sem = Integer.parseInt(hashMap.get("sem").toString());
        int quarter = Integer.parseInt(hashMap.get("quarter").toString());
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        Date date = null;
        try {
            date = formatter.parse(deadLine);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        RoomShiftClass classes = roomShiftClassesService.findById(classCode);
        TeacherResources teacherResources = teacherResourceService.findById(resourceCode);
        TeacherQuizzes quizzes =
                new TeacherQuizzes(code, highGrade, lowGrade, quarter, sem, description,
                        new Date(), date, teacherResources,
                        classes);
        teacherQuizzesService.save(quizzes);

        for (Student student : classes.getStudents()) {
            studentQuizService.save(
                    new StudentQuiz(0, -1, student, quizzes, new Date())
            );
        }

        teacherResources.setStatus(1);
        teacherResourceService.save(teacherResources);

        return new ResponseEntity<>(
                new Response<>("Quiz Create Success", "Successful"),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/quiz/delete")
    public ResponseEntity<Response<?>> deleteQuiz(@RequestParam("code") String code, @RequestParam("email") String email) {
        TeacherQuizzes quizzes = teacherQuizzesService.findTeacherQuizzesByCode(code, email);
        if (quizzes == null) {
            return new ResponseEntity<>(
                    new Response<>("Quiz Code Is Not Existing", null),
                    HttpStatus.BAD_REQUEST
            );
        }
        teacherQuizzesService.deleteById(code);
        if(quizzes.getResource().getTeacherQuizzes().size() ==0){
            quizzes.getResource().setStatus(0);
            teacherResourceService.save(quizzes.getResource());
        }
        return new ResponseEntity<>(
                new Response<>("Delete Quiz Success", "Success"),
                HttpStatus.OK
        );
    }
}
