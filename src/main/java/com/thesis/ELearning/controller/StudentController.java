package com.thesis.ELearning.controller;

import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.entity.Teacher;
import com.thesis.ELearning.entity.TeacherResources;
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
@RequestMapping("/teacher")
public class StudentController {

    private final StudentAssignmentService assignmentService;

    @Autowired
    public StudentController(StudentAssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

//    @PostMapping("/upload/assignment")
//    private ResponseEntity<Response<?>> uploadResource(
//            @RequestParam("filePath") String filePath,
//            @RequestParam("name") String name,
//            @RequestParam("type") String type,
//            @RequestParam("description") String description,
//            @RequestParam("code") String code,
//            @RequestParam("email") String email
//    ) {
//        Teacher teacher = teacherRepository.getTeacherByUserEmail(email);
//        TeacherResources teacherResources = new TeacherResources(code, name, filePath, type, description, new Date());
//        teacherResources.setStatus("Not Shared");
//        teacherResources.setTeacher(teacher);
//        teacherResourceService.save(teacherResources);
//
//        return new ResponseEntity<>(
//                new Response<>("Create Resource Successful", "Resource Upload Success"),
//                HttpStatus.OK
//        );
//
//    }
}
