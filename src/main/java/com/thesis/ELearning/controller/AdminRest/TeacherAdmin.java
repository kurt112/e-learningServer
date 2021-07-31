package com.thesis.ELearning.controller.AdminRest;

import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.entity.Teacher;
import com.thesis.ELearning.entity.User;
import com.thesis.ELearning.service.serviceImplementation.TeacherService;
import com.thesis.ELearning.service.serviceImplementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class TeacherAdmin {

     final private TeacherService teacherService;
     final private UserService userService;

     @Autowired
    public TeacherAdmin(TeacherService teacherService, UserService userService) {
        this.teacherService = teacherService;
        this.userService = userService;
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
        Teacher teacherID = new Teacher(email,user,"?");
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

        Teacher teacher = teacherService.findById(id);

        User user = teacher.getUser();
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
        teacherService.save(teacher);

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


}
