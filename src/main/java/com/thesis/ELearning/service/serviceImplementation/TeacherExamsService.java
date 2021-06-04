package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.API.ApiSettings;
import com.thesis.ELearning.entity.TeacherExams;
import com.thesis.ELearning.repository.TeacherExamsRepository;
import com.thesis.ELearning.service.PageableService.PageableServiceTeacherExams;
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
import java.util.Optional;

@Transactional
@Service
@GraphQLApi
public class TeacherExamsService implements PageableServiceTeacherExams {

    private final TeacherExamsRepository repo;
    private int totalPages = 0;
    private long totalElements = 0;
    private int currentPages = 0;

    @Autowired
    public TeacherExamsService(TeacherExamsRepository repo) {
        this.repo = repo;
    }

    @GraphQLQuery(name = "getTeacherExams")
    @Override
    public List<TeacherExams> getTeacherExams(@GraphQLArgument(name = "search") String search, @GraphQLArgument(name = "email") String email, @GraphQLArgument(name = "page") int page) {
        Pageable pageable = PageRequest.of(page,10);
        Page<TeacherExams> pages = repo.getTeacherExams(search,email,pageable);
        totalElements =  pages.getTotalElements();
        totalPages = pages.getTotalPages();
        currentPages = page;
        return pages.getContent();
    }

    @Override
    public TeacherExams findTeacherExamByCode(String code, String email) {
        return repo.getTeacherExamsByCodeAndEmail(code, email);
    }

    @Override
    public List<TeacherExams> data(String search, int page) {
        return null;
    }

    @Override
    public TeacherExams save(TeacherExams teacherExams) {
        try {
            repo.save(teacherExams);
        }catch (Exception e){
            return null;
        }
        return teacherExams;
    }

    @Override
    public boolean deleteById(String id) {
        repo.deleteById(id);
        return true;
    }

    @Override
    public TeacherExams findById(String id) {
        Optional<TeacherExams> teacherExams = repo.findById(id);
        return teacherExams.orElse(null);
    }

    @GraphQLQuery(name = "getTeacherExamsSettings")
    @Override
    public ApiSettings apiSettings() {
        return new ApiSettings(totalElements, totalPages, currentPages);
    }

}
