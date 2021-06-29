package com.thesis.ELearning.service.PageableService;

import com.thesis.ELearning.entity.Student;
import com.thesis.ELearning.service.PagableParentClass.ServicesGraphQl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PageableServiceStudent extends ServicesGraphQl<Student> {

    Student getStudentById(String id);

}
