package com.thesis.ELearning.service.PageableService;

import com.thesis.ELearning.entity.RoomShiftClass;
import com.thesis.ELearning.entity.Student;
import com.thesis.ELearning.entity.StudentAssignment;
import com.thesis.ELearning.service.PagableParentClass.ServicesGraphQl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PageableServiceStudent extends ServicesGraphQl<Student> {

    Student getStudentById(String id);

    List<RoomShiftClass> getStudentClass(String email, int status);

    List<StudentAssignment> getStudentAssignment(String email);

    List<StudentAssignment> getStudentArchiveAssignment(String email,int page);

}
