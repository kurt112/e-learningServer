package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.API.ApiSettings;
import com.thesis.ELearning.entity.StudentAssignment;
import com.thesis.ELearning.repository.StudentAssignmentRepository;
import com.thesis.ELearning.service.PageableService.PageableServiceStudentAssignment;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @Author Kurt Orioque
 */

@Transactional
@Service
@GraphQLApi
public class StudentAssignmentService implements PageableServiceStudentAssignment {

    private final StudentAssignmentRepository repo;
    private int totalPages = 0;
    private long totalElements = 0;
    private int currentPages = 0;

    @Autowired
    public StudentAssignmentService(StudentAssignmentRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<StudentAssignment> data(String search, int page) {
        Pageable pageable = PageRequest.of(page,10);
        Page<StudentAssignment> pages = repo.findAll(pageable);
        totalElements =  pages.getTotalElements();
        totalPages = pages.getTotalPages();
        currentPages = page;
        return pages.getContent();
    }

    @Override
    public StudentAssignment save(StudentAssignment studentAssignment) {
        repo.save(studentAssignment);
        return studentAssignment;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }

    @Override
    public StudentAssignment findById(String id) {

        Optional<StudentAssignment> studentAssignment =repo.findById(Integer.parseInt(id));

        return studentAssignment.orElse(null);
    }

    @Override
    public ApiSettings apiSettings() {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }
}
