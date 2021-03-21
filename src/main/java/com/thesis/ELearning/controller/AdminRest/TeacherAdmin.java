package com.thesis.ELearning.controller.AdminRest;

import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.entity.Student;
import com.thesis.ELearning.entity.Teacher;
import com.thesis.ELearning.entity.User;
import com.thesis.ELearning.service.serviceImplementation.TeacherService;
import com.thesis.ELearning.service.serviceImplementation.UserService;
import com.thesis.ELearning.utils.FormattedDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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
    public ResponseEntity<Response<Teacher>> addTeacher(@RequestParam("id") String id){
        if(teacherService.findById(id) != null){
            return  ResponseEntity
                    .badRequest()
                    .body(new Response<>("Teacher is already exist", null));
        }

        User user = new User(""+id,"?","?","?", "?","?","?",FormattedDate.getDateNowWithTime(),"?","TEACHER",false,false,false,false);
        Teacher teacherID = new Teacher(id,user,"?");
        teacherID.setId(id);
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

        User user = new User(email,firstName,middleName,lastName,suffix,gender,password,FormattedDate.getDateNow(),birthDate,"TEACHER",
                true,true,true,true);

        Teacher teacher = teacherService.findById(id);

        if(teacher!=null){
            String emailUser = teacher.getUser().getEmail();
            teacher.setUser(user);
            teacherService.save(teacher);
            System.out.println("Success");
            userService.deleteById(emailUser);

            return new ResponseEntity<>(
                    new Response<>("Register Teacher Success", teacher),
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                new Response<>("Register Teacher Success", null),
                HttpStatus.OK
        );
    }


}
