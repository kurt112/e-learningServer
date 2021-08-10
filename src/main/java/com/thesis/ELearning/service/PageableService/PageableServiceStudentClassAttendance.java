package com.thesis.ELearning.service.PageableService;

import com.thesis.ELearning.entity.StudentClassAttendance;
import com.thesis.ELearning.service.PagableParentClass.ServicesGraphQl;
import org.springframework.stereotype.Service;

@Service
public interface PageableServiceStudentClassAttendance extends ServicesGraphQl<StudentClassAttendance> {
    long countByClass(String classId);

    StudentClassAttendance getLast();
}
