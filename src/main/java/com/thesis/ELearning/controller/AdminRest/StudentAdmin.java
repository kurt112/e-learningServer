package com.thesis.ELearning.controller.AdminRest;

import com.thesis.ELearning.service.serviceImplementation.StudentService;
import com.thesis.ELearning.utils.FormattedDate;
import com.thesis.ELearning.entity.API.PagesContent;
import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.entity.Student;
import com.thesis.ELearning.entity.User;
import com.thesis.ELearning.service.PagableParentClass.ServicePageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class StudentAdmin {

    final private StudentService studentService;


    public StudentAdmin(StudentService studentService) {
        this.studentService = studentService;
    }

//    @GetMapping("/students")
//    public List<Student> students(@RequestParam("search") String search, @RequestParam("page") int page){
//        List<Student> pages = studentService.data(search,page);
//
//        return  pages;
//    }


//    @PostMapping("/student-register")
//    public ResponseEntity<Response<Student>> student(@RequestParam("id") String id){
//
//        if(studentServices.findById(id) !=null){
//            return new ResponseEntity<>(
//                    new Response<>("Student is already exist", null),
//                    HttpStatus.BAD_REQUEST
//            );
//        }
//
//        User user = new User(""+id,"?","?","?", FormattedDate.getDateNowWithTime(),"?");
//        Student student = new Student(id, user);
//
//
//        studentServices.save(student);
//
//        return new ResponseEntity<>(
//                new Response<>("Register Student Success", student),
//                HttpStatus.OK
//        );
//    }



}
