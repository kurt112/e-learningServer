package com.thesis.ELearning.controller.AdminRest;

import com.thesis.ELearning.entity.API.PagesContent;
import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.entity.Subject;
import com.thesis.ELearning.service.PagableParentClass.ServicePageable;
import com.thesis.ELearning.service.serviceImplementation.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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


//    @GetMapping("/subjects")
//    public PagesContent<Subject> subjects(@RequestParam("search") String search, @RequestParam("page") int page){
//        Page<Subject> pages = subjectServices.data(search,page);
//        return new PagesContent<>(pages.getContent(),pages.getTotalElements(),pages.getTotalPages(),pages.getNumber());
//    }

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
