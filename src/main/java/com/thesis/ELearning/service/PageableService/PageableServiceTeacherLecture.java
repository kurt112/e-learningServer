package com.thesis.ELearning.service.PageableService;

import com.thesis.ELearning.entity.TeacherLectures;
import com.thesis.ELearning.service.PagableParentClass.ServicesGraphQl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PageableServiceTeacherLecture extends ServicesGraphQl<TeacherLectures> {
    List<TeacherLectures> getTeacherLecture(String search, String email,int page);
    TeacherLectures findTeacherLectureByCode(String code, String email);
}
