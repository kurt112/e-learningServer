package com.thesis.ELearning.service.PageableService;

import com.thesis.ELearning.entity.TeacherExams;
import com.thesis.ELearning.entity.TeacherLectures;
import com.thesis.ELearning.service.PagableParentClass.ServicesGraphQl;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PageableServiceTeacherExams extends ServicesGraphQl<TeacherExams> {
    List<TeacherExams> getTeacherExams(String search, String email, int page);
    TeacherExams findTeacherExamByCode(String code, String email);
}
