package com.thesis.ELearning.controller;

import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.entity.StudentAssignment;
import com.thesis.ELearning.entity.StudentExam;
import com.thesis.ELearning.entity.StudentQuiz;
import com.thesis.ELearning.service.serviceImplementation.StudentAssignmentService;
import com.thesis.ELearning.service.serviceImplementation.StudentExamService;
import com.thesis.ELearning.service.serviceImplementation.StudentQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentAssignmentService assignmentService;
    private final StudentQuizService quizService;
    private final StudentExamService examService;


    @Autowired
    public StudentController(StudentAssignmentService assignmentService, StudentQuizService quizService, StudentExamService examService) {
        this.assignmentService = assignmentService;
        this.quizService = quizService;
        this.examService = examService;
    }

    @PostMapping("/upload/assignment")
    private ResponseEntity<Response<?>> uploadAssignment(
            @RequestParam("fileName") String fileName,
            @RequestParam("id") int id
    ) {

        StudentAssignment studentAssignment = assignmentService.findById(""+id);
        System.out.println(studentAssignment);
        Date datePass = new Date();
        if(datePass.before(studentAssignment.getTeacherAssignment().getDeadLine()))studentAssignment.setResponse("On Time");
        else studentAssignment.setResponse("Late");
        studentAssignment.setSubmittedAt(new Date());
        studentAssignment.setLocation(fileName);
        studentAssignment.setStatus(1);
        assignmentService.save(studentAssignment);

        return new ResponseEntity<>(
                new Response<>("Pass Assignment Successful", "Assignment Pass Succesful"),
                HttpStatus.OK
        );

    }

    @PostMapping("/unsubmit/assignment")
    private ResponseEntity<Response<?>> unSubmitAssigment(
            @RequestParam("id") int id
    ) {

        StudentAssignment studentAssignment = assignmentService.findById(""+id);

        studentAssignment.setSubmittedAt(null);
        studentAssignment.setResponse("");
        studentAssignment.setLocation("");
        studentAssignment.setStatus(-1);
        studentAssignment.setGrade(0);
        assignmentService.save(studentAssignment);

        return new ResponseEntity<>(
                new Response<>("UnSubmit Assignment Successful", "Assignment UnSubmit Succesful"),
                HttpStatus.OK
        );

    }

    // for quiz
    @PostMapping("/upload/quiz")
    private ResponseEntity<Response<?>> uploadQuiz(
            @RequestParam("fileName") String fileName,
            @RequestParam("id") int id
    ) {

        StudentQuiz studentQuiz =quizService.findById(""+id);
        Date datePass = new Date();
        if(datePass.before(studentQuiz.getQuiz().getDeadLine()))studentQuiz.setResponse("On Time");
        else studentQuiz.setResponse("Late");
        studentQuiz.setSubmittedAt(new Date());
        studentQuiz.setLocation(fileName);
        studentQuiz.setStatus(1);
        quizService.save(studentQuiz);

        return new ResponseEntity<>(
                new Response<>("Pass Quiz Successful", "Quiz Pass Succesful"),
                HttpStatus.OK
        );

    }

    @PostMapping("/unsubmit/quiz")
    private ResponseEntity<Response<?>> unSubmitQuiz(
            @RequestParam("id") int id
    ) {

        StudentQuiz studentQuiz =quizService.findById(""+id);

        studentQuiz.setSubmittedAt(null);
        studentQuiz.setResponse("");
        studentQuiz.setLocation("");
        studentQuiz.setStatus(-1);
        studentQuiz.setGrade(0);
        quizService.save(studentQuiz);

        return new ResponseEntity<>(
                new Response<>("UnSubmit Quiz Successful", "Quiz UnSubmit Succesful"),
                HttpStatus.OK
        );

    }

    //for exam
    @PostMapping("/upload/exam")
    private ResponseEntity<Response<?>> uploadExam(
            @RequestParam("fileName") String fileName,
            @RequestParam("id") int id
    ) {

        StudentExam studentExam = examService.findById(""+id);
        Date datePass = new Date();
        if(datePass.before(studentExam.getExam().getDeadLine()))studentExam.setResponse("On Time");
        else studentExam.setResponse("Late");
        studentExam.setSubmittedAt(new Date());
        studentExam.setLocation(fileName);
        studentExam.setStatus(1);
        examService.save(studentExam);

        return new ResponseEntity<>(
                new Response<>("Pass Exam Successful", "Exam Pass Succesful"),
                HttpStatus.OK
        );

    }

    @PostMapping("/unsubmit/exam")
    private ResponseEntity<Response<?>> unSubmitExam(
            @RequestParam("id") int id
    ) {

        StudentExam studentExam = examService.findById(""+id);

        studentExam.setSubmittedAt(null);
        studentExam.setResponse("");
        studentExam.setLocation("");
        studentExam.setStatus(-1);
        studentExam.setGrade(0);
        examService.save(studentExam);

        return new ResponseEntity<>(
                new Response<>("UnSubmit Exam Successful", "Exam UnSubmit Succesful"),
                HttpStatus.OK
        );

    }

    @PostMapping("/grade/exam")
    private ResponseEntity<Response<?>> gradeExam(
            @RequestParam("id") int id,
            @RequestParam("grade") double grade
    ) {

        StudentExam studentExam = examService.findById(""+id);
        studentExam.setGrade(grade);

        examService.save(studentExam);

        return new ResponseEntity<>(
                new Response<>("Grade Successful", "Grade3 Succesful"),
                HttpStatus.OK
        );

    }

    @PostMapping("/grade/assignment")
    private ResponseEntity<Response<?>> unSubmitAssigment(
            @RequestParam("id") int id,
            @RequestParam("grade") double grade
    ) {
        StudentAssignment studentAssignment = assignmentService.findById(""+id);
        studentAssignment.setGrade(grade);
        assignmentService.save(studentAssignment);

        return new ResponseEntity<>(
                new Response<>("Assignment Graded Success", "Assignment Graded Success"),
                HttpStatus.OK
        );

    }


}
