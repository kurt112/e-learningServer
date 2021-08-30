package com.thesis.ELearning.service.PageableService;

import com.thesis.ELearning.entity.*;
import com.thesis.ELearning.service.PagableParentClass.ServicesGraphQl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PageableServiceTeacher extends ServicesGraphQl<Teacher> {
    List<TeacherResources> getTeacherResources(String search, String email, int page,int status);

    Teacher getTeacherById(String id);

    List<StudentAssignment> getTeacherAssignmentToGrade(String email);

    List<StudentExam> getTeacherExamToGrade(String email);

    List<StudentQuiz> getTeacherQuizToGrade(String email);


    List<TeacherExams> getTeacherExam(String email);

    List<TeacherAssignment> getTeacherAssignment(String email);

    List<TeacherQuizzes> getTeacherQuizzes(String email);


}
