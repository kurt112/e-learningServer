package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.API.ApiSettings;
import com.thesis.ELearning.entity.TeacherQuizzes;
import com.thesis.ELearning.repository.TeacherQuizzesRepository;
import com.thesis.ELearning.service.PageableService.PageableServiceTeacherQuizzes;
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
public class TeacherQuizzesService implements PageableServiceTeacherQuizzes {

    private final TeacherQuizzesRepository repo;
    private int totalPages = 0;
    private long totalElements = 0;
    private int currentPages = 0;

    @Autowired
    public TeacherQuizzesService(TeacherQuizzesRepository repo) {
        this.repo = repo;
    }

    @GraphQLQuery(name = "getTeacherQuizzes")
    @Override
    public List<TeacherQuizzes> getTeacherQuizzes(@GraphQLArgument(name = "search") String search, @GraphQLArgument(name = "email") String email, @GraphQLArgument(name = "page") int page){
        Pageable pageable = PageRequest.of(page,10);
        Page<TeacherQuizzes> pages = repo.getTeacherQuizzes(search,email,pageable);
        totalElements =  pages.getTotalElements();
        totalPages = pages.getTotalPages();
        currentPages = page;
        return pages.getContent();
    }

    @Override
    public TeacherQuizzes findTeacherQuizzesByCode(String code, String email) {
        return repo.getTeacherQuizzesByCodeAndEmail(code,email);
    }

    @Override
    public List<TeacherQuizzes> data(String search, int page) {
        return null;
    }

    @Override
    public TeacherQuizzes save(TeacherQuizzes teacherQuizzes) {
        try {
            repo.save(teacherQuizzes);
        }catch (Exception e){
            return  null;
        }
        return teacherQuizzes;
    }

    @Override
    public boolean deleteById(String id) {
        repo.deleteById(id);
        return true;
    }

    @Override
    public TeacherQuizzes findById(String id) {
        return null;
    }

    @GraphQLQuery(name = "getTeacherQuizSettings")
    @Override
    public ApiSettings apiSettings() {
        return new ApiSettings(totalElements, totalPages, currentPages);
    }

}
