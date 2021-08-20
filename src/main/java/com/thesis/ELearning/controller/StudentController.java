package com.thesis.ELearning.controller;

import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.entity.StudentAssignment;
import com.thesis.ELearning.service.serviceImplementation.StudentAssignmentService;
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


    @Autowired
    public StudentController(StudentAssignmentService assignmentService) {
        this.assignmentService = assignmentService;
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
        studentAssignment.setStatus(0);
        assignmentService.save(studentAssignment);

        return new ResponseEntity<>(
                new Response<>("UnSubmit Assignment Successful", "Assignment UnSubmit Succesful"),
                HttpStatus.OK
        );

    }
}
