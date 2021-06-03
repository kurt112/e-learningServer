package com.thesis.ELearning.service.PageableService;

import com.thesis.ELearning.entity.TeacherAssignment;
import com.thesis.ELearning.service.PagableParentClass.ServicesGraphQl;
import org.springframework.stereotype.Service;

@Service
public interface PageableServiceTeacherAssignment extends ServicesGraphQl<TeacherAssignment> {
    TeacherAssignment getRoomShiftClassAssignment(String code, String email);
}
