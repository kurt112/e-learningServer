package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.API.ApiSettings;
import com.thesis.ELearning.entity.StudentQuiz;
import com.thesis.ELearning.repository.StudentQuizRepository;
import com.thesis.ELearning.service.PageableService.PageableServiceStudentQuiz;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @Author Kurt Orioque
 */
@Transactional
@Service
@GraphQLApi
public class StudentQuizService implements PageableServiceStudentQuiz {

    private final StudentQuizRepository repo;

    private int totalPages = 0;
    private long totalElements = 0;
    private int currentPages = 0;

    public StudentQuizService(StudentQuizRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<StudentQuiz> data(String search, int page,@GraphQLArgument(name= "status") int status) {
        return null;
    }

    @Override
    public StudentQuiz save(StudentQuiz studentQuiz) {
        repo.save(studentQuiz);
        return studentQuiz;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }

    @Override
    public StudentQuiz findById(String id) {
        Optional<StudentQuiz> quiz = repo.findById(Integer.parseInt(id));
        return quiz.orElse(null);
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
