package com.thesis.ELearning.service.PageableService;

import com.thesis.ELearning.entity.Subject;
import com.thesis.ELearning.service.PagableParentClass.ServicesGraphQl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PageableServiceSubject extends ServicesGraphQl<Subject> {

    List<Subject> searchSubjectByNameAndCode(String search, int page);

}
