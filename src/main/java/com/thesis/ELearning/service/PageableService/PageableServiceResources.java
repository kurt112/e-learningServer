package com.thesis.ELearning.service.PageableService;

import com.thesis.ELearning.entity.TeacherResources;
import com.thesis.ELearning.service.PagableParentClass.ServicesGraphQl;
import org.springframework.stereotype.Service;

@Service
public interface PageableServiceResources extends ServicesGraphQl<TeacherResources> {

    TeacherResources FindResourceByTeacherEmail(String code, String email);

}
