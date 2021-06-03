package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.API.ApiSettings;
import com.thesis.ELearning.entity.TeacherLectures;
import com.thesis.ELearning.repository.TeacherLectureRepository;
import com.thesis.ELearning.service.PageableService.PageableServiceTeacherLecture;
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
public class TeacherLectureService implements PageableServiceTeacherLecture {

    private final TeacherLectureRepository repo;
    private int totalPages = 0;
    private long totalElements = 0;
    private int currentPages = 0;

    @Autowired
    public TeacherLectureService(TeacherLectureRepository repo) {
        this.repo = repo;
    }

    @GraphQLQuery(name = "getTeacherLecture")
    @Override
    public List<TeacherLectures> getTeacherLecture(@GraphQLArgument(name = "search") String search,
                                                   @GraphQLArgument(name = "email") String email,
                                                   @GraphQLArgument(name = "page") int page) {

        Pageable pageable = PageRequest.of(page,10);
        Page<TeacherLectures> pages = repo.getTeacherLecture(search,email,pageable);
        totalElements =  pages.getTotalElements();
        totalPages = pages.getTotalPages();
        currentPages = page;
        return pages.getContent();
    }

    @Override
    public TeacherLectures findTeacherLectureByCode(String code, String email) {
        return repo.getTeacherLecturesByCodeAndEmail(code, email);
    }

    @Override
    public List<TeacherLectures> data(String search, int page) {
        Pageable pageable = PageRequest.of(page,10);
        return repo.findAll(pageable).getContent();
    }

    @Override
    public TeacherLectures save(TeacherLectures teacherLectures) {
        try {
            repo.save(teacherLectures);
        }catch (Exception e){
            return null;
        }
        return teacherLectures;
    }

    @Override
    public boolean deleteById(String id) {
        repo.deleteById(id);
        return true;
    }

    @Override
    public TeacherLectures findById(String id) {
        return null;
    }

    @GraphQLQuery(name = "getTeacherLectureSettings")
    @Override
    public ApiSettings apiSettings() {
        return new ApiSettings(totalElements, totalPages, currentPages);
    }

}
