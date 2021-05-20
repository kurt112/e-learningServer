package com.thesis.ELearning.controller;

import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.entity.Resources;
import com.thesis.ELearning.entity.Teacher;
import com.thesis.ELearning.repository.TeacherRepository;
import com.thesis.ELearning.service.serviceImplementation.ResourceService;
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
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherRepository teacherRepository;
    private final ResourceService resourceService;
    private final StorageService storageService;


    @Autowired
    public TeacherController(TeacherRepository teacherRepository, ResourceService resourceService, StorageService storageService) {
        this.teacherRepository = teacherRepository;
        this.resourceService = resourceService;

        this.storageService = storageService;
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
        Resources resources = new Resources(code, name, "", type, description, new Date());
        resources.setStatus("Not Shared");
        try {
            resources.addTeacher(teacher);
            String location = storageService.UploadResource(file, resources, teacher);
            resources.setLocation(location);
            resourceService.save(resources);
        } catch (IOException e) {
            return new ResponseEntity<>(
                    new Response<>("Resource Already Exist", null),
                    HttpStatus.BAD_REQUEST
            );
        }

        String[] date = resources.getCreatedAt().split("\\s+");


        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("code", code);
        hashMap.put("name", name);
        hashMap.put("date", date[1] + " " + date[2] + " " + date[date.length - 1]);
        hashMap.put("type", type);
        hashMap.put("status", resources.getStatus());
        hashMap.put("location", resources.getLocation());

        return new ResponseEntity<>(
                new Response<>("Create Resource Successful", hashMap),
                HttpStatus.OK
        );

    }

    @DeleteMapping("/delete/resource")
    public ResponseEntity<Response<?>> deleteFile(@RequestParam("code") String code) {
        Resources resource = resourceService.findById(code);
        if(resource == null){
            return new ResponseEntity<>(
                    new Response<>("Document Code Is Not Existing", null),
                    HttpStatus.BAD_REQUEST
            );
        }
        File resource_file = new File(resource.getLocation());
        if(resource_file.delete()) resourceService.deleteById(resource.getCode());
        return new ResponseEntity<>(
                new Response<>("Delete Resource Success",code),
                HttpStatus.OK
        );
    }

    @GetMapping("/resource/download")
    public ResponseEntity<Object> downloadFile(@RequestParam("code") String code) throws IOException {

        Resources resources = resourceService.findById(code);
        File resource_file = new File(resources.getLocation());
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
}
