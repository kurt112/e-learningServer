package com.thesis.ELearning.controller.AdminRest;

import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.entity.Curriculum;
import com.thesis.ELearning.entity.Subject;
import com.thesis.ELearning.service.serviceImplementation.CurriculumService;
import com.thesis.ELearning.service.serviceImplementation.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class CurriculumAdmin {

    private final CurriculumService curriculumService;
    private final SubjectService subjectService;

    @Autowired
    public CurriculumAdmin(CurriculumService service, SubjectService subjectService) {
        this.curriculumService = service;
        this.subjectService = subjectService;
    }

    @PostMapping("/curriculum-register")
    public ResponseEntity<Response<?>> AddRoom(@RequestBody Curriculum curriculum){
        curriculumService.save(curriculum);
        return new ResponseEntity<>(
                new Response<>("Register Room Success", "Success"),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/curriculum")
    public ResponseEntity<Response<?>> deleteCurriculum(@RequestParam("id") String id) {
        Curriculum curriculum = curriculumService.findById(id);
        if(curriculum == null){
            return new ResponseEntity<>(
                    new Response<>("Curriculum Id Is Not Existing", "Curriculum Delete Not Success"),
                    HttpStatus.BAD_REQUEST
            );
        }

        curriculumService.deleteById(id);

        return new ResponseEntity<>(
                new Response<>("Delete Curriculum Success", "Curriculum Delete Success"),
                HttpStatus.OK
        );
    }



    @PostMapping("/curriculum/add/subject")
    public ResponseEntity<Response<?>> curriculumAddSubject(@RequestBody HashMap<Object, Object> hashMap){

        List <String> subjectsCode = (List<String>) hashMap.get("subjects");
        String curriculumCode = hashMap.get("curriculumCode").toString();
        List<Subject>  subjects = new ArrayList<>();

        System.out.println(subjects);
        for(String code: subjectsCode){
            subjects.add(subjectService.findById(code));
        }

        Curriculum curriculum = curriculumService.findById(curriculumCode);
        curriculum.setSubjects(subjects);
        curriculumService.save(curriculum);



        return new ResponseEntity<>(
                new Response<>("Success Add Subject", "Curriculum Subject Added"),
                HttpStatus.OK
        );
    }

}
