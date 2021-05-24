package com.thesis.ELearning.service.PageableService;

import com.thesis.ELearning.entity.Resources;
import com.thesis.ELearning.entity.Teacher;
import com.thesis.ELearning.service.PagableParentClass.ServicesGraphQl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PageableServiceTeacher extends ServicesGraphQl<Teacher> {
    List<Resources> getTeacherResources(String search, String email, int page);
}
