package com.thesis.ELearning.controller.AdminRest;

import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.entity.Curriculum;
import com.thesis.ELearning.entity.Room;
import com.thesis.ELearning.service.serviceImplementation.CurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class CurriculumAdmin {
    private final CurriculumService service;

    @Autowired
    public CurriculumAdmin(CurriculumService service) {
        this.service = service;
    }

    @PostMapping("/curriculum-register")
    public ResponseEntity<Response<?>> AddRoom(@RequestBody Curriculum curriculum){
        service.save(curriculum);
        return new ResponseEntity<>(
                new Response<>("Register Room Success", "Success"),
                HttpStatus.OK
        );
    }

}
