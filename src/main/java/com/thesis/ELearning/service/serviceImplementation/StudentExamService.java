package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.API.ApiSettings;
import com.thesis.ELearning.entity.StudentExam;
import com.thesis.ELearning.repository.StudentExamRepository;
import com.thesis.ELearning.service.PageableService.PageableServiceStudentExam;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
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
public class StudentExamService implements PageableServiceStudentExam {

    private final StudentExamRepository repo;

    private int totalPages = 0;
    private long totalElements = 0;
    private int currentPages = 0;

    public StudentExamService(StudentExamRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<StudentExam> data(String search, int page) {
        return null;
    }

    @Override
    public StudentExam save(StudentExam studentExam) {
        repo.save(studentExam);
        return studentExam;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }

    @Override
    public StudentExam findById(String id) {
        Optional<StudentExam> exam = repo.findById(Integer.parseInt(id));
        return exam.orElse(null);
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
