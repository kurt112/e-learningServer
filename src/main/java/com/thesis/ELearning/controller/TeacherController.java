package com.thesis.ELearning.controller;

import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.entity.Resources;
import com.thesis.ELearning.entity.Teacher;
import com.thesis.ELearning.repository.ResourceRepository;
import com.thesis.ELearning.repository.TeacherRepository;
import com.thesis.ELearning.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherRepository teacherRepository;
    private final ResourceRepository resourceRepository;
    private final StorageService storageService;


    @Autowired
    public TeacherController(TeacherRepository teacherRepository, ResourceRepository resourceRepository, StorageService storageService) {
        this.teacherRepository = teacherRepository;
        this.resourceRepository = resourceRepository;
        this.storageService = storageService;
    }

    @PostMapping("/upload/resource")
    private ResponseEntity<Response<?>> uploadResource (
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("type") String type,
            @RequestParam("description") String description,
            @RequestParam("code") String code,
            @RequestParam("email") String email
    ) {

        System.out.println(code);
        Teacher teacher  = teacherRepository.getTeacherByUserEmail(email);
//        Resources resources = new Resources(code,name,"",type,description,new Date());
//        try {
//            resources.addTeacher(teacher);
//            String location = storageService.UploadResource(file,resources,teacher);
//            resources.setLocation(location);
//            resourceRepository.save(resources);
//        }catch (IOException e){
//            return new ResponseEntity<>(
//                    new Response<>("Resource Already Exist", null),
//                    HttpStatus.BAD_REQUEST
//            );
//        }

//        String [] date = resources.getCreatedAt().split("\\s+");


        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("code",code);
        hashMap.put("name", name);
//        hashMap.put("date", date[1] + " " + date[2] + " " + date[date.length-1]);
        hashMap.put("type", type);
//        hashMap.put("status", resources.getStatus());

//        System.out.println(Arrays.toString(date));
//        hashMap.put("location", resources.getLocation());

        return new ResponseEntity<>(
                new Response<>("Create Resource Successful", hashMap),
                HttpStatus.OK
        );

    }
}
