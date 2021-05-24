package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.API.ApiSettings;
import com.thesis.ELearning.entity.RoomShiftClass;
import com.thesis.ELearning.entity.Teacher;
import com.thesis.ELearning.repository.TeacherRepository;
import com.thesis.ELearning.service.PageableService.PageableServiceTeacher;
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
public class TeacherService implements PageableServiceTeacher {
    final private TeacherRepository repo;
    private int totalPages = 0;
    private long totalElements = 0;
    private int currentPages = 0;
    @Autowired
    public TeacherService(TeacherRepository repo) {
        this.repo = repo;
    }


    @Override
    @GraphQLQuery(name = "teachers")
    public List<Teacher> data(@GraphQLArgument(name = "search") String search, @GraphQLArgument(name = "page") int page) {
        Pageable pageable = PageRequest.of(page,10);
        Page<Teacher> pages = repo.Teachers(search, pageable);
        totalElements =  pages.getTotalElements();
        totalPages = pages.getTotalPages();
        currentPages = page;
        return pages.getContent();
    }

    @Override
    public Teacher save(Teacher teacherID) {
        try {
            repo.save(teacherID);
        }catch (Exception e){
            return null;
        }
        return teacherID;
    }

    @Override
    public boolean deleteById(String id) {
        try {
            repo.deleteById(id);
        }catch (Exception e){
            return  false;
        }
        return true;
    }

    @Override
    @GraphQLQuery(name = "getTeacherByUserEmail")
    public Teacher findById(@GraphQLArgument(name = "email") String email) {

        return repo.getTeacherByUserEmail(email);
    }

    @GraphQLQuery(name = "getTeacherClasses")
    public List<RoomShiftClass> getTeacherRoomClass(@GraphQLArgument(name = "teacherId") String teacherId, @GraphQLArgument(name = "status") int status) {

        return repo.getTeacherClass(teacherId, status);
    }

    @Override
    @GraphQLQuery(name = "teacherSettings")
    public ApiSettings apiSettings() {
        return new ApiSettings(totalElements, totalPages, currentPages);
    }
}
