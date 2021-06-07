package com.thesis.ELearning.controller.AdminRest;

import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.entity.Subject;
import com.thesis.ELearning.service.serviceImplementation.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin")
@RestController
public class SubjectAdmin {

    final private SubjectService subjectServices;

    @Autowired
    public SubjectAdmin(SubjectService subjectServices) {
        this.subjectServices = subjectServices;
    }


    @PostMapping(value = "/subjectID-register",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Response<Subject>> AddSubject(@RequestBody Subject subjectID) {
        System.out.println(subjectID);
        if(subjectServices.findById(subjectID.getSubjectCode()) != null){
            return new ResponseEntity<>(
                    new Response<>("Subject is already Exist", null),
                    HttpStatus.BAD_REQUEST
            );
        }
        subjectServices.save(subjectID);

        return new ResponseEntity<>(
                new Response<>("Subject Register Success", subjectID),
                HttpStatus.OK
        );
    }

}
