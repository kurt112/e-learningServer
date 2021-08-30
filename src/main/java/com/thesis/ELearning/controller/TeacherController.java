package com.thesis.ELearning.controller;

import com.thesis.ELearning.entity.*;
import com.thesis.ELearning.entity.API.Response;
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
import java.util.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;
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

    // for adding grade
    private final StudentGradeService studentGradeService;


    @Autowired
    public TeacherController(TeacherService teacherService, TeacherResourceService teacherResourceService, TeacherAssignmentService teacherAssignmentService, RoomShiftClassesService roomShiftClassesService, TeacherLectureService lectureService, TeacherExamsService teacherExamsService, TeacherQuizzesService teacherQuizzesService, StudentAssignmentService studentAssignmentService, StudentExamService studentExamService, StudentQuizService studentQuizService, StudentGradeService studentGradeService) {
        this.teacherService = teacherService;
        this.teacherResourceService = teacherResourceService;
        this.teacherAssignmentService = teacherAssignmentService;
        this.roomShiftClassesService = roomShiftClassesService;
        this.lectureService = lectureService;
        this.teacherExamsService = teacherExamsService;
        this.teacherQuizzesService = teacherQuizzesService;
        this.studentAssignmentService = studentAssignmentService;
        this.studentExamService = studentExamService;
        this.studentQuizService = studentQuizService;
        this.studentGradeService = studentGradeService;
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
        Teacher teacher = teacherService.findById(email);
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

        if (lectures.getResource().getTeacherLectures().size() == 0) {
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

        if (exams.getResource().getTeacherExams().size() == 0) {
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
        if (quizzes.getResource().getTeacherQuizzes().size() == 0) {
            quizzes.getResource().setStatus(0);
            teacherResourceService.save(quizzes.getResource());
        }
        return new ResponseEntity<>(
                new Response<>("Delete Quiz Success", "Success"),
                HttpStatus.OK
        );
    }

    @PostMapping("/grade/student")
    public ResponseEntity<Response<?>> createGrade(@RequestParam("email") String email) {


        List<TeacherAssignment> getTeacherAssignment = teacherService.getTeacherAssignment(email);
        List<TeacherExams> getTeacherExams = teacherService.getTeacherExam(email);
        List<TeacherQuizzes> getTeacherQuizzes = teacherService.getTeacherQuizzes(email);


        // teacher activity per classes points

        // String -> class id
        // Double -> Total Score for that acitivty

        HashMap<RoomShiftClass, Double> teacherAssignmentScore = new HashMap<>();
        HashMap<RoomShiftClass, Double> teacherQuizscore = new HashMap<>();
        HashMap<RoomShiftClass, Double> teacherExamScore = new HashMap<>();


        // First String -> class id
        // Second String -> student id
        // Double Student Score -> Student Score

        HashMap<RoomShiftClass, HashMap<Student, Double>> studentAssignmentScore = new HashMap<>();
        HashMap<RoomShiftClass, HashMap<Student, Double>> studentExamScore = new HashMap<>();
        HashMap<RoomShiftClass, HashMap<Student, Double>> studentQuizScore = new HashMap<>();


        for (TeacherAssignment teacherAssignment : getTeacherAssignment) {

            RoomShiftClass classId = teacherAssignment.getRoomShiftClass();

            // storing highGrade then adding it
            teacherAssignmentScore.putIfAbsent(classId, 0.0);
            teacherAssignmentScore.put(classId, teacherAssignmentScore.get(classId) + teacherAssignment.getHighGrade());
            System.out.println(teacherAssignmentScore);
            for (StudentAssignment studentAssignment : teacherAssignment.getStudentAssignments()) {

                Student id = studentAssignment.getStudent();

                studentAssignmentScore.putIfAbsent(classId, new HashMap<>());
                studentAssignmentScore.get(classId).putIfAbsent(id, 0.0);

                // get student and then add the pass score to new score
                studentAssignmentScore.get(classId).put(
                        id, studentAssignmentScore.get(classId).get(id) + studentAssignment.getGrade()
                );

            }

        }

        for (TeacherQuizzes quizzes : getTeacherQuizzes) {

            RoomShiftClass classId = quizzes.getRoomShiftClass();

            teacherQuizscore.putIfAbsent(classId, 0.0);
            teacherQuizscore.put(classId, teacherQuizscore.get(classId) + quizzes.getHighGrade());


            for (StudentQuiz studentQuiz : quizzes.getStudentQuizs()) {

                Student id = studentQuiz.getStudent();

                studentQuizScore.putIfAbsent(classId, new HashMap<>());
                studentQuizScore.get(classId).putIfAbsent(id, 0.0);

                // get student and then add the pass score to new score
                studentQuizScore.get(classId).put(
                        id, studentQuizScore.get(classId).get(id) + studentQuiz.getGrade()
                );
            }

        }

        for (TeacherExams teacherExams : getTeacherExams) {

            RoomShiftClass classId = teacherExams.getRoomShiftClass();

            teacherExamScore.putIfAbsent(classId, 0.0);
            teacherExamScore.put(classId, teacherExamScore.get(classId) + teacherExams.getHighGrade());

            System.out.println(teacherExamScore);
            for (StudentExam studentExam : teacherExams.getStudentExams()) {

                Student id = studentExam.getStudent();

                studentExamScore.putIfAbsent(classId, new HashMap<>());
                studentExamScore.get(classId).putIfAbsent(id, 0.0);

                // get student and then add the pass score to new score
                studentExamScore.get(classId).put(
                        id, studentExamScore.get(classId).get(id) + studentExam.getGrade()
                );
            }

        }


        System.out.println("===================================");

        // double -> grade in class student

        HashMap<RoomShiftClass, HashMap<Student, Double>> finalClassGradeInStudent = new HashMap<>();

        for (RoomShiftClass classId : teacherAssignmentScore.keySet()) {

            finalClassGradeInStudent.putIfAbsent(classId, new HashMap<>());

            if(studentAssignmentScore.get(classId) ==null)continue;

            for (Student studentId : studentAssignmentScore.get(classId).keySet()) {

                finalClassGradeInStudent.get(classId).putIfAbsent(studentId, 0.0);
                double finalGradeAssignment = ((studentAssignmentScore.get(classId).get(studentId) / teacherAssignmentScore.get(classId)) * 100) * .20;
                System.out.println("The final assignment " + finalGradeAssignment);
                finalClassGradeInStudent
                        .get(classId)
                        .put(studentId,
                                finalClassGradeInStudent
                                        .get(classId).get(studentId) + finalGradeAssignment);


            }


        }

        for (RoomShiftClass classId : teacherExamScore.keySet()) {

            finalClassGradeInStudent.putIfAbsent(classId, new HashMap<>());

            if(studentExamScore.get(classId) ==null)continue;

            for (Student studentId : studentExamScore.get(classId).keySet()) {

                finalClassGradeInStudent.get(classId).putIfAbsent(studentId, 0.0);
                double finalGradeExam = ((studentExamScore.get(classId).get(studentId) / teacherExamScore.get(classId)) * 100) * .50;

                System.out.println("The final grade " + finalGradeExam);
                finalClassGradeInStudent
                        .get(classId)
                        .put(studentId,
                                finalClassGradeInStudent
                                        .get(classId).get(studentId) + finalGradeExam);


            }
        }

        for (RoomShiftClass classId : teacherQuizscore.keySet()) {

            finalClassGradeInStudent.putIfAbsent(classId, new HashMap<>());

            System.out.println(studentQuizScore.get(classId));

            if(studentQuizScore.get(classId) == null) continue;

            for (Student studentId : studentQuizScore.get(classId).keySet()) {

                finalClassGradeInStudent.get(classId).putIfAbsent(studentId, 0.0);
                double finalGradeQuiz = ((studentQuizScore.get(classId).get(studentId) / teacherQuizscore.get(classId)) * 100) * .30;

                System.out.println("The final quiz " + finalGradeQuiz);

                finalClassGradeInStudent
                        .get(classId)
                        .put(studentId,
                                finalClassGradeInStudent
                                        .get(classId).get(studentId) + finalGradeQuiz);

            }
        }

        System.out.println(teacherAssignmentScore);
        System.out.println(teacherExamScore);
        System.out.println(teacherQuizscore);
        System.out.println("===================================");
        System.out.println(finalClassGradeInStudent);

        for(RoomShiftClass classId: finalClassGradeInStudent.keySet()){

            Map<Student,Double> map = finalClassGradeInStudent.get(classId);

            for(Student studentId: map.keySet()){

                StudentGrade studentGrade = studentGradeService.studentGrade(classId.getId(), studentId.getId());

                double finalGrade = map.get(studentId);

                if(studentGrade == null){
                    studentGradeService.save(new StudentGrade(classId, finalGrade, studentId));
                    continue;
                }

                System.out.println("the final grade " + finalGrade);

                studentGrade.setGrade(finalGrade);
                studentGradeService.save(studentGrade);


            }


         }


        return new ResponseEntity<>(
                new Response<>("Student Grde Success", "Success"),
                HttpStatus.OK
        );
    }
}
