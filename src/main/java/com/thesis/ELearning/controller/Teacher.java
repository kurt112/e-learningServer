package com.thesis.ELearning.controller;

import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.entity.PostEntity.Post_TeacherUploadActivity;
import com.thesis.ELearning.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class Teacher {

    private final TeacherRepository repo;


    @Autowired
    public Teacher(TeacherRepository repo) {
        this.repo = repo;
    }


    @PostMapping("/upload/resource")
    private ResponseEntity<Response<Post_TeacherUploadActivity>> uploadResouce () {



        return null;
    }
}
