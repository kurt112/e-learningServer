package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.API.ApiSettings;
import com.thesis.ELearning.entity.TeacherAssignment;
import com.thesis.ELearning.repository.TeacherAssignmentRepository;
import com.thesis.ELearning.service.PageableService.PageableServiceTeacherAssignment;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@GraphQLApi
public class TeacherAssignmentService implements PageableServiceTeacherAssignment {

    private final TeacherAssignmentRepository repo;
    private int totalPages = 0;
    private long totalElements = 0;
    private int currentPages = 0;

    @Autowired
    public TeacherAssignmentService(TeacherAssignmentRepository repo) {
        this.repo = repo;
    }

    @GraphQLQuery(name = "getTeacherAssignment")
    public List<TeacherAssignment> getRoomShiftClassAssignmentByTeacher(@GraphQLArgument(name = "search") String search, @GraphQLArgument(name = "email") String email, @GraphQLArgument(name = "page") int page){
        System.out.println(email);
        Pageable pageable = PageRequest.of(page, 10);
        Page<TeacherAssignment> pages = repo.getRoomShiftClassAssignmentByTeacher(search,email, pageable);
        totalElements = pages.getTotalElements();
        totalPages = pages.getTotalPages();
        currentPages = page;
        return pages.getContent();
    }


    @Override
    public List<TeacherAssignment> data(@GraphQLArgument(name = "search") String search, @GraphQLArgument(name = "page") int page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<TeacherAssignment> pages = repo.data(search, pageable);
        totalElements = pages.getTotalElements();
        totalPages = pages.getTotalPages();
        currentPages = page;
        return pages.getContent();
    }

    @Override
    public TeacherAssignment save(TeacherAssignment teacherAssignment) {

        return repo.save(teacherAssignment);
    }

    @Override
    public boolean deleteById(String id) {
        repo.deleteById(id);
        return true;
    }

    @Override
    public TeacherAssignment findById(String id) {
        return null;
    }

    @Override
    @GraphQLQuery(name = "teacherAssignmentSettings")
    public ApiSettings apiSettings() {
        return new ApiSettings(totalElements, totalPages, currentPages);
    }

    @Override
    public TeacherAssignment getRoomShiftClassAssignment(String code, String email) {
        return repo.getRoomShiftClassAssignment(code,email);
    }

    @Override
    public long count() {
        return repo.count();
    }
}
