package com.thesis.ELearning.controller.AdminRest;

import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.entity.Student;
import com.thesis.ELearning.entity.User;
import com.thesis.ELearning.service.serviceImplementation.StudentService;
import com.thesis.ELearning.service.serviceImplementation.UserService;
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

        User user = new User(""+id,"?","?","?","?","","","",new Date(),"STUDENT",false,false,false,false,new Date(), new Date());

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


        Student student = studentService.findById(id);

        User user = student.getUser();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        user.setSuffix(suffix);
        user.setGender(gender);
        user.setPassword(password);
        user.setAccountNotExpired(true);
        user.setAccountNotLocked(true);
        user.setCredentialNotExpired(true);
        user.setEnabled(true);


        userService.save(user);
        studentService.save(student);


        return new ResponseEntity<>(
                new Response<>("Register Student Success", null),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/student")
    public ResponseEntity<Response<?>> deleteStudent(@RequestParam("id") String id) {

        Student student = studentService.findById(id);
        if(student == null){
            return new ResponseEntity<>(
                    new Response<>("Student Id Is Not Existing", null),
                    HttpStatus.BAD_REQUEST
            );
        }
        String email = student.getUser().getEmail();
        studentService.deleteById(id);
//        userService.deleteById(email);
        return new ResponseEntity<>(
                new Response<>("Delete Student Success", "Student Delete Success"),
                HttpStatus.OK
        );
    }



}
