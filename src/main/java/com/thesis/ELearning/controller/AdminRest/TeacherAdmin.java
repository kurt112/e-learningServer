package com.thesis.ELearning.controller.AdminRest;

import com.thesis.ELearning.service.serviceImplementation.TeacherService;
import com.thesis.ELearning.utils.FormattedDate;
import com.thesis.ELearning.entity.API.PagesContent;
import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.entity.Teacher;
import com.thesis.ELearning.entity.User;
import com.thesis.ELearning.service.PagableParentClass.ServicePageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public class TeacherAdmin {

//     final private TeacherService teacherService;
//    @Autowired
//    public TeacherAdmin(TeacherService teacherService) {
//
//        this.teacherService = teacherService;
//    }
//
//
//    @PostMapping("/teacherID-register")
//    public ResponseEntity<Response<Teacher>> addTeacher(@RequestParam("id") String id){
//
//        if(teacherServices.findById(id) != null){
//            return  ResponseEntity
//                    .badRequest()
//                    .body(new Response<>("Teacher is already exist", null));
//        }
//
//        User user = new User(""+id,"?","?","?", FormattedDate.getDateNowWithTime(),"?");
//        Teacher teacherID = new Teacher(id,user,"?");
//        teacherID.setId(id);
//        teacherID.setUser(user);
////        userServices.save(user);
//        teacherServices.save(teacherID);
//        return new ResponseEntity<>(
//                new Response<>("Register Teacher is Success", teacherID),
//                HttpStatus.OK
//        );
//    }


}
