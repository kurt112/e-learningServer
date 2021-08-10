package com.thesis.ELearning.service.PageableService;

import com.thesis.ELearning.entity.TeacherResources;
import com.thesis.ELearning.entity.Teacher;
import com.thesis.ELearning.service.PagableParentClass.ServicesGraphQl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PageableServiceTeacher extends ServicesGraphQl<Teacher> {
    List<TeacherResources> getTeacherResources(String search, String email, int page);

    Teacher getTeacherById(String id);
}
