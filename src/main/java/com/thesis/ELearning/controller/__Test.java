package com.thesis.ELearning.controller;

import com.thesis.ELearning.entity.Teacher;
import com.thesis.ELearning.entity.User;
import com.thesis.ELearning.service.serviceImplementation.StudentService;
import com.thesis.ELearning.service.serviceImplementation.TeacherService;
import com.thesis.ELearning.service.serviceImplementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class __Test {
    private final UserService userService;
    private final StudentService studentService;
    private final TeacherService teacherService;

    @Autowired
    public __Test(UserService userService, StudentService studentService, TeacherService teacherService) {
        this.userService = userService;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @GetMapping("/")
    public String createDataText() {

        if(userService.findByEmail("kurt@email.com") == null){

            User elly_A = new User(7,"elly@email.com", "?", "?","", "?", "?", "?", "?", new Date(), "ADMIN", true, true, true, true, new Date(), new Date());
            User eve_A = new User(8,"kurt@email.com", "?", "?", "", "?", "","?", "?", new Date(), "ADMIN", true, true, true, true, new Date(), new Date());
            User kurt_A = new User(9,"eve@email.com", "?", "?", "", "?","", "?", "?", new Date(), "ADMIN", true, true, true, true, new Date(), new Date());

            Teacher ellyTeacherAdmin = new Teacher("JhXpaH", null, "?");
            Teacher eveTeacherAdmin = new Teacher("NngSZe", null, "?");
            Teacher kurtTeacherAdmin = new Teacher("sW9Dtv", null, "?");

            ellyTeacherAdmin.setUser(elly_A);
            eveTeacherAdmin.setUser(eve_A);
            kurtTeacherAdmin.setUser(kurt_A);

            teacherService.save(ellyTeacherAdmin);
            teacherService.save(eveTeacherAdmin);
            teacherService.save(kurtTeacherAdmin);

        }



        return "welcome";
    }
}
