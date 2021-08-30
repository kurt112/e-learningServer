package com.thesis.ELearning.service.PageableService;

import com.thesis.ELearning.entity.StudentGrade;
import com.thesis.ELearning.service.PagableParentClass.ServicesGraphQl;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public interface PageableServiceStudentGrade extends ServicesGraphQl<StudentGrade> {

    StudentGrade studentGrade(String classId, String studentId);

}
