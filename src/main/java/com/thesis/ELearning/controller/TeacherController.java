package com.thesis.ELearning.controller;

import com.thesis.ELearning.entity.*;
import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.repository.TeacherRepository;
import com.thesis.ELearning.service.serviceImplementation.*;
import com.thesis.ELearning.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private final StorageService storageService;
    private final TeacherAssignmentService teacherAssignmentService;
    private final RoomShiftClassesService roomShiftClassesService;
    private final TeacherLectureService lectureService;
    private final TeacherExamsService teacherExamsService;
    private final TeacherQuizzesService teacherQuizzesService;


    @Autowired
    public TeacherController(TeacherRepository teacherRepository, TeacherResourceService teacherResourceService, StorageService storageService, TeacherAssignmentService teacherAssignmentService, RoomShiftClassesService roomShiftClassesService, TeacherLectureService lectureService, TeacherExamsService teacherExamsService, TeacherQuizzesService teacherQuizzesService) {
        this.teacherRepository = teacherRepository;
        this.teacherResourceService = teacherResourceService;
        this.storageService = storageService;
        this.teacherAssignmentService = teacherAssignmentService;
        this.roomShiftClassesService = roomShiftClassesService;
        this.lectureService = lectureService;
        this.teacherExamsService = teacherExamsService;
        this.teacherQuizzesService = teacherQuizzesService;
    }

    @PostMapping("/upload/resource")
    private ResponseEntity<Response<?>> uploadResource(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("type") String type,
            @RequestParam("description") String description,
            @RequestParam("code") String code,
            @RequestParam("email") String email
    ) {
        Teacher teacher = teacherRepository.getTeacherByUserEmail(email);
        TeacherResources teacherResources = new TeacherResources(code, name, "", type, description, new Date());
        teacherResources.setStatus("Not Shared");
        try {
            String location = storageService.UploadResource(file, teacherResources, teacher);
            teacherResources.setLocation(location);
            teacherResources.setTeacher(teacher);
            teacherResourceService.save(teacherResources);
        } catch (IOException e) {
            return new ResponseEntity<>(
                    new Response<>("Resource Already Exist", null),
                    HttpStatus.BAD_REQUEST
            );
        }
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
        File resource_file = new File(resource.getLocation());

        if(resource_file.delete()) System.out.println("Delete Resource Success");

        return new ResponseEntity<>(
                new Response<>("Delete Resource Success", code),
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

        TeacherAssignment assignment = teacherAssignmentService.getRoomShiftClassAssignment(code,email);

        if(assignment == null){
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
        int quarter =  Integer.parseInt(hashMap.get("quarter").toString());
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        Date date = null;
        try {
            date = formatter.parse(deadLine);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        RoomShiftClass classes = roomShiftClassesService.findById(classCode);
        TeacherResources teacherResources = teacherResourceService.findById(resourceCode);
        TeacherAssignment assignment =
                new TeacherAssignment(code,highGrade,lowGrade,quarter,sem,description,
                        new Date(),date, teacherResources,
                        classes);

        teacherAssignmentService.save(assignment);



        return new ResponseEntity<>(
                new Response<>("Assignment Create Success", "Successful"),
                HttpStatus.OK
        );
    }

    @PostMapping("/lecture/create")
    public ResponseEntity<Response<?>> createLecture(@RequestBody HashMap<Object, Object> hashMap){
        int quarter =  Integer.parseInt(hashMap.get("quarter").toString());
        int sem = Integer.parseInt(hashMap.get("sem").toString());
        RoomShiftClass classes = roomShiftClassesService.findById(hashMap.get("classCode").toString());
        String code = hashMap.get("code").toString();
        TeacherResources resources  = teacherResourceService.findById(hashMap.get("resourceCode").toString());
        String description = hashMap.get("description").toString();
        lectureService.save(new TeacherLectures(code,description,quarter,sem,resources,classes, new Date(), null));
        return new ResponseEntity<>(
                new Response<>("Lecture  Create Success", "Successful"),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/lecture/delete")
    public ResponseEntity<Response<?>> deleteLecture(@RequestParam("code") String code, @RequestParam("email") String email) {

        TeacherLectures lectures = lectureService.findTeacherLectureByCode(code,email);

        if(lectures == null){
            return new ResponseEntity<>(
                    new Response<>("Lecture Code Is Not Existing", null),
                    HttpStatus.BAD_REQUEST
            );
        }

        lectureService.deleteById(code);

        return new ResponseEntity<>(
                new Response<>("Delete Assignment Success", code),
                HttpStatus.OK
        );
    }

    @PostMapping("/exam/create")
    public ResponseEntity<Response<?>> createExam(@RequestBody HashMap<Object, Object> hashMap){
        String classCode = (String) hashMap.get("classCode");
        double highGrade = Double.parseDouble(hashMap.get("highGrade").toString());
        double lowGrade = Double.parseDouble(hashMap.get("lowGrade").toString());
        String deadLine = hashMap.get("deadLine").toString();
        String resourceCode = (String) hashMap.get("resourceCode");
        String code = (String) hashMap.get("code");
        String description = (String) hashMap.get("description");
        int sem = Integer.parseInt(hashMap.get("sem").toString());
        int quarter =  Integer.parseInt(hashMap.get("quarter").toString());
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
                new TeacherExams(code,highGrade,lowGrade,quarter,sem,description,
                        new Date(),date, teacherResources,
                        classes);

        teacherExamsService.save(exams);



        return new ResponseEntity<>(
                new Response<>("Exam Create Success", "Successful"),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/exam/delete")
    public ResponseEntity<Response<?>> deleteExam(@RequestParam("code") String code, @RequestParam("email") String email) {

        TeacherExams exams = teacherExamsService.findTeacherExamByCode(code,email);

        if(exams == null){
            return new ResponseEntity<>(
                    new Response<>("Exam Code Is Not Existing", null),
                    HttpStatus.BAD_REQUEST
            );
        }

        teacherExamsService.deleteById(code);

        return new ResponseEntity<>(
                new Response<>("Delete Exam Success", "Success"),
                HttpStatus.OK
        );
    }

    @PostMapping("/quiz/create")
    public ResponseEntity<Response<?>> createQuiz(@RequestBody HashMap<Object, Object> hashMap){
        String classCode = (String) hashMap.get("classCode");
        double highGrade = Double.parseDouble(hashMap.get("highGrade").toString());
        double lowGrade = Double.parseDouble(hashMap.get("lowGrade").toString());
        String deadLine = hashMap.get("deadLine").toString();
        String resourceCode = (String) hashMap.get("resourceCode");
        String code = (String) hashMap.get("code");
        String description = (String) hashMap.get("description");
        int sem = Integer.parseInt(hashMap.get("sem").toString());
        int quarter =  Integer.parseInt(hashMap.get("quarter").toString());
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
                new TeacherQuizzes(code,highGrade,lowGrade,quarter,sem,description,
                        new Date(),date, teacherResources,
                        classes);
        teacherQuizzesService.save(quizzes);
        return new ResponseEntity<>(
                new Response<>("Quiz Create Success", "Successful"),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/quiz/delete")
    public ResponseEntity<Response<?>> deleteQuiz(@RequestParam("code") String code, @RequestParam("email") String email) {
        TeacherQuizzes quizzes = teacherQuizzesService.findTeacherQuizzesByCode(code,email);
        if(quizzes == null){
            return new ResponseEntity<>(
                    new Response<>("Quiz Code Is Not Existing", null),
                    HttpStatus.BAD_REQUEST
            );
        }
        teacherQuizzesService.deleteById(code);
        return new ResponseEntity<>(
                new Response<>("Delete Quiz Success", "Success"),
                HttpStatus.OK
        );
    }
}
