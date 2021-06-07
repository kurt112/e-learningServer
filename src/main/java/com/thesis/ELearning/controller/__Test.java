package com.thesis.ELearning.controller;

import com.thesis.ELearning.entity.Student;
import com.thesis.ELearning.entity.Teacher;
import com.thesis.ELearning.entity.User;
import com.thesis.ELearning.service.serviceImplementation.StudentService;
import com.thesis.ELearning.service.serviceImplementation.TeacherService;
import com.thesis.ELearning.service.serviceImplementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller()
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
        User elly_T = new User("wT2c7n", "?", "?", "?", "?", "?", "?", "?", "TEACHER", false, false, false, false, new Date(), new Date());
        User elly_S = new User("C075Xn", "?", "?", "?", "?", "?", "?", "?", "STUDENT", false, false, false, false, new Date(), new Date());
        User eve_T = new User("svngDF", "?", "?", "?", "?", "?", "?", "?", "TEACHER", false, false, false, false, new Date(), new Date());
        User eve_S = new User("ygsSqU", "?", "?", "?", "?", "?", "?", "?", "STUDENT", false, false, false, false, new Date(), new Date());
        User kurt_T = new User("g3eDgy", "?", "?", "?", "?", "?", "?", "?", "TEACHER", false, false, false, false, new Date(), new Date());
        User kurt_S = new User("MqtP0G", "?", "?", "?", "?", "?", "?", "?", "STUDENT", false, false, false, false, new Date(), new Date());

        User elly_A = new User("JhXpaH", "?", "?", "?", "?", "?", "?", "?", "ADMIN", false, false, false, false, new Date(), new Date());
        User eve_A = new User("NngSZe", "?", "?", "?", "?", "?", "?", "?", "ADMIN", false, false, false, false, new Date(), new Date());
        User kurt_A = new User("sW9Dtv", "?", "?", "?", "?", "?", "?", "?", "ADMIN", false, false, false, false, new Date(), new Date());

        userService.save(elly_A);
        userService.save(eve_A);
        userService.save(kurt_A);

        userService.save(elly_T);
        userService.save(elly_S);
        userService.save(eve_T);
        userService.save(eve_S);
        userService.save(kurt_T);
        userService.save(kurt_S);

        Teacher ellyTeacherAdmin = new Teacher("JhXpaH", elly_A, "?");
        Teacher eveTeacherAdmin = new Teacher("NngSZe", eve_A, "?");
        Teacher kurtTeacherAdmin = new Teacher("sW9Dtv", kurt_A, "?");

        teacherService.save(ellyTeacherAdmin);
        teacherService.save(eveTeacherAdmin);
        teacherService.save(kurtTeacherAdmin);

        Teacher ellyTeacher = new Teacher("wT2c7n", elly_T, "?");
        Teacher eveTeacher = new Teacher("svngDF", eve_T, "?");
        Teacher kurtTeacher = new Teacher("g3eDgy", kurt_T, "?");

        teacherService.save(ellyTeacher);
        teacherService.save(eveTeacher);
        teacherService.save(kurtTeacher);


        // elly role
        Student ellyStudent = new Student("C075Xn", elly_S);
        Student eveStudent = new Student("ygsSqU", eve_S);
        Student kurtStudent = new Student("MqtP0G", kurt_S);

        studentService.save(ellyStudent);
        studentService.save(kurtStudent);
        studentService.save(eveStudent);

        return "welcome";
    }
}
