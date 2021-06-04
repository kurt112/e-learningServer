package com.thesis.ELearning.service.PageableService;
import com.thesis.ELearning.entity.TeacherQuizzes;
import com.thesis.ELearning.service.PagableParentClass.ServicesGraphQl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PageableServiceTeacherQuizzes extends ServicesGraphQl<TeacherQuizzes> {
    List<TeacherQuizzes> getTeacherQuizzes(String search, String email, int page);
    TeacherQuizzes findTeacherQuizzesByCode(String code, String email);

}
