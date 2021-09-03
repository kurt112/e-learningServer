package com.thesis.ELearning.controller.AdminRest;

import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.entity.Teacher;
import com.thesis.ELearning.entity.User;
import com.thesis.ELearning.service.EmailService.EmailSenderService;
import com.thesis.ELearning.service.EmailService.EmailType;
import com.thesis.ELearning.service.MyUserDetailsService;
import com.thesis.ELearning.service.serviceImplementation.TeacherService;
import com.thesis.ELearning.service.serviceImplementation.UserService;
import com.thesis.ELearning.utils.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class TeacherAdmin {

     final private TeacherService teacherService;
     final private UserService userService;
     final private Jwt jwt;
     final private  MyUserDetailsService userDetailsService;

     @Autowired
    public TeacherAdmin(TeacherService teacherService, UserService userService, Jwt jwt, MyUserDetailsService userDetailsService) {
        this.teacherService = teacherService;
        this.userService = userService;
         this.jwt = jwt;
         this.userDetailsService = userDetailsService;
     }

    @PostMapping("/teacherID-register")
    public ResponseEntity<Response<Teacher>> addTeacher(@RequestParam("id") String email){
        Teacher teacher = teacherService.findById(email);
        if(teacher != null){
            return  ResponseEntity
                    .badRequest()
                    .body(new Response<>("Teacher is already exist", null));
        }

        User user = new User(email,"?","?","?", "?","?","","?",new Date(),"TEACHER",false,false,false,false,new Date(),new Date());
        Teacher teacherID = new Teacher(email,user);
        teacherID.setId(email);
        teacherID.setUser(user);
        userService.save(user);
        teacherService.save(teacherID);
        return new ResponseEntity<>(
                new Response<>("Register Teacher is Success", teacherID),
                HttpStatus.OK
        );
    }


    @PostMapping("/teacher-fillUp")
    public ResponseEntity<Response<Teacher>> updateUser(@RequestParam("id") String id,
                                                        @RequestParam("first-name") String firstName,
                                                        @RequestParam("middle-name") String middleName,
                                                        @RequestParam("last-name") String lastName,
                                                        @RequestParam("suffix") String suffix,
                                                        @RequestParam("birth-date") String birthDate,
                                                        @RequestParam("gender") String gender,
                                                        @RequestParam("email") String email,
                                                        @RequestParam("password") String password){

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = formatter.parse(birthDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        User user = new User();
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
        user.setUserRole("TEACHER");
        user.setBirthdate(date);

        Teacher teacher = new Teacher(id, user);

        userService.save(user);
        teacherService.save(teacher);

        Thread thread = new Thread(() ->{
            EmailSenderService mailer = new EmailSenderService();

            final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            final String token = this.jwt.generateToken(userDetails,false);

            try {
                mailer.sendEmail(email, "Verify Email", EmailType.userVerify(token));
            } catch (Exception ex) {
                System.out.println("Failed to sent email.");
            }
        });

        thread.start();

        return new ResponseEntity<>(
                new Response<>("Register Teacher Success", teacher),
                HttpStatus.OK
        );

    }

    @DeleteMapping("/delete/teacher")
    public ResponseEntity<Response<?>> deleteSubject(@RequestParam("id") String id) {
        Teacher teacher = teacherService.findById(id);


        if(teacher == null){
            return new ResponseEntity<>(
                    new Response<>("Teacher Id Is Not Existing", null),
                    HttpStatus.BAD_REQUEST
            );
        }

        teacherService.deleteById(id);


        return new ResponseEntity<>(
                new Response<>("Delete Teacher Success", "Subject Teacher Success"),
                HttpStatus.OK
        );
    }

    @PostMapping("/off/teacher")
    public ResponseEntity<Response<?>> setTeacherOff(@RequestParam("id") String id) {

        Teacher teacher = teacherService.findById(id);
        teacher.setStatus(0);
        teacherService.save(teacher);

        return new ResponseEntity<>(
                new Response<>("Teacher On", "Teacher On"),
                HttpStatus.OK
        );
    }

    @PostMapping("/on/teacher")
    public ResponseEntity<Response<?>> setTeacherOn(@RequestParam("id") String id) {

        Teacher teacher  = teacherService.findById(id);
        teacher.setStatus(1);
        teacherService.save(teacher);

        return new ResponseEntity<>(
                new Response<>("Teacher Off", "Teacher Off"),
                HttpStatus.OK
        );
    }


}
