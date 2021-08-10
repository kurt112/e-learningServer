package com.thesis.ELearning.service.PageableService;

import com.thesis.ELearning.entity.StudentClassAttendance;
import com.thesis.ELearning.entity.TeacherClassAttendance;
import com.thesis.ELearning.service.PagableParentClass.ServicesGraphQl;
import org.springframework.stereotype.Service;

@Service
public interface PageableServiceTeacherClassAttendance extends ServicesGraphQl<TeacherClassAttendance> {
    long countByClass(String classId);


    TeacherClassAttendance getLast();
}
