package com.thesis.ELearning.controller;

import com.thesis.ELearning.entity.DashBoard;
import com.thesis.ELearning.entity.Teacher;
import com.thesis.ELearning.entity.User;
import com.thesis.ELearning.service.serviceImplementation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class __Test {

    private final UserService userService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final DashboardService dashboardService;
    private final SubjectService subjectService;
    private final RoomService roomService;
    private final RoomShiftClassesService roomShiftClassesService;

    @Autowired
    public __Test(UserService userService, StudentService studentService, TeacherService teacherService, DashboardService dashboardService, SubjectService subjectService, RoomService roomService, RoomShiftClassesService roomShiftClassesService) {
        this.userService = userService;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.dashboardService = dashboardService;
        this.subjectService = subjectService;
        this.roomService = roomService;
        this.roomShiftClassesService = roomShiftClassesService;
    }

    @GetMapping("/setup")
    public String createDataText() {

        if (userService.findByEmail("kurt@email.com") == null) {

            User elly_A = new User(7, "elly@email.com", "?", "?", "", "?", "?", "?", "?", new Date(), "ADMIN", true, true, true, true, new Date(), new Date());
            User eve_A = new User(8, "kurt@email.com", "?", "?", "", "?", "", "?", "?", new Date(), "ADMIN", true, true, true, true, new Date(), new Date());
            User kurt_A = new User(9, "eve@email.com", "?", "?", "", "?", "", "?", "?", new Date(), "ADMIN", true, true, true, true, new Date(), new Date());

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

        dashboardService.save(
                new DashBoard(1,
                        (int)teacherService.count(),
                        (int)subjectService.count(),
                        (int)studentService.count(),
                        (int)roomService.count(),
                        (int)roomShiftClassesService.count()
                )
        );


        return "welcome";
    }
}
