package com.thesis.ELearning.controller.AdminRest;

import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.entity.Student;
import com.thesis.ELearning.entity.User;
import com.thesis.ELearning.service.serviceImplementation.StudentService;
import com.thesis.ELearning.service.serviceImplementation.UserService;
import com.thesis.ELearning.utils.FormattedDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class StudentAdmin {

    final private StudentService studentService;
    final private UserService userService;


    @Autowired

    public StudentAdmin(StudentService studentService, UserService userService) {
        this.studentService = studentService;
        this.userService = userService;
    }




    @PostMapping("/student-register")
    public ResponseEntity<Response<Student>> student(@RequestParam("id") String id){

        if(studentService.findById(id) !=null){
            return new ResponseEntity<>(
                    new Response<>("Student is already exist", null),
                    HttpStatus.BAD_REQUEST
            );
        }

        User user = new User(""+id,"?","?","?", FormattedDate.getDateNowWithTime(),"?","","","","STUDENT",false,false,false,false,new Date(), new Date());

        Student student = new Student(id, user);

        userService.save(user);
        studentService.save(student);

        return new ResponseEntity<>(
                new Response<>("Register Student Success", student),
                HttpStatus.OK
        );
    }

    @PostMapping("/student-fillUp")
    public ResponseEntity<Response<Student>> updateUser(@RequestParam("id") String id,
                                                     @RequestParam("first-name") String firstName,
                                                     @RequestParam("middle-name") String middleName,
                                                     @RequestParam("last-name") String lastName,
                                                     @RequestParam("suffix") String suffix,
                                                     @RequestParam("birth-date") String birthDate,
                                                     @RequestParam("gender") String gender,
                                                     @RequestParam("email") String email,
                                                     @RequestParam("password") String password){

        User user = new User(email,firstName,middleName,lastName,suffix,gender,password,FormattedDate.getDateNow(),birthDate,"STUDENT",
                true,true,true,true,new Date(), new Date());

        Student student = studentService.findById(id);

        if(student!=null){
            String emailUser = student.getUser().getEmail();
            student.setUser(user);
            studentService.save(student);
            userService.deleteById(emailUser);

            return new ResponseEntity<>(
                    new Response<>("Register Student Success", student),
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                new Response<>("Register Student Success", null),
                HttpStatus.OK
        );
    }



}
