package com.thesis.ELearning.controller.AdminRest;

import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.entity.RoomShift;
import com.thesis.ELearning.entity.Student;
import com.thesis.ELearning.entity.User;
import com.thesis.ELearning.service.EmailService.EmailSenderService;
import com.thesis.ELearning.service.EmailService.EmailType;
import com.thesis.ELearning.service.MyUserDetailsService;
import com.thesis.ELearning.service.serviceImplementation.StudentService;
import com.thesis.ELearning.service.serviceImplementation.UserService;
import com.thesis.ELearning.utils.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class StudentAdmin {

    final private StudentService studentService;
    final private UserService userService;
    final private Jwt jwt;
    final private MyUserDetailsService userDetailsService;



    @Autowired

    public StudentAdmin(StudentService studentService, UserService userService, Jwt jwt, MyUserDetailsService userDetailsService) {
        this.studentService = studentService;
        this.userService = userService;
        this.jwt = jwt;
        this.userDetailsService = userDetailsService;
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


        Student student = studentService.getStudentById(id);

        User user = student.getUser();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        user.setSuffix(suffix);
        user.setGender(gender);
        user.setPassword(password);
        user.setAccountNotExpired(true);
        user.setCredentialNotExpired(true);
        user.setAccountNotLocked(true);

        Thread thread = new Thread(() -> {
            EmailSenderService mailer = new EmailSenderService();


            final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            final String token = this.jwt.generateToken(userDetails,false);

            try {
                mailer.sendEmail(email, "Verify Email", EmailType.userVerify(token));
                System.out.println("Email sent.");
            } catch (Exception ex) {
                System.out.println("Failed to sent email.");
                ex.printStackTrace();
            }
        });

        thread.start();


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

    @PostMapping("/off/student")
    public ResponseEntity<Response<?>> setStudentOff(@RequestParam("id") String id) {

        Student student = studentService.findById(id);
        student.setStatus(0);
        studentService.save(student);

        return new ResponseEntity<>(
                new Response<>("Student On", "Student On"),
                HttpStatus.OK
        );
    }

    @PostMapping("/on/student")
    public ResponseEntity<Response<?>> setStudentOn(@RequestParam("id") String id) {

        Student student  = studentService.findById(id);
        student.setStatus(1);
        studentService.save(student);

        return new ResponseEntity<>(
                new Response<>("Student Off", "Student Off"),
                HttpStatus.OK
        );
    }



}
