package com.thesis.ELearning.service.PageableService;

import com.thesis.ELearning.entity.*;
import com.thesis.ELearning.service.PagableParentClass.ServicesGraphQl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PageableServiceStudent extends ServicesGraphQl<Student> {

    Student getStudentById(String id);

    List<RoomShiftClass> getStudentClass(String email, int status);

    List<StudentAssignment> getStudentAssignment(String email);

    List<StudentAssignment> getStudentArchiveAssignment(String email,int page);

    List<StudentExam> getStudentExam(String email);

    List<StudentExam> getStudentExamArchive(String email, int page);

    List<StudentQuiz> getStudentQuiz(String email);

    List<StudentQuiz> getStudentQuizArchive(String email, int page);


}
