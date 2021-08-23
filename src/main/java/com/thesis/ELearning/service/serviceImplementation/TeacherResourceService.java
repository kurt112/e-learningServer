package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.API.ApiSettings;
import com.thesis.ELearning.entity.TeacherResources;
import com.thesis.ELearning.repository.TeacherResourceRepository;
import com.thesis.ELearning.service.PageableService.PageableServiceResources;
import io.leangen.graphql.annotations.GraphQLArgument;
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
public class TeacherResourceService implements PageableServiceResources {

    private final TeacherResourceRepository repo;
    private int totalPages = 0;
    private long totalElements = 0;
    private int currentPages = 0;

    @Autowired
    public TeacherResourceService(TeacherResourceRepository repo) {
        this.repo = repo;
    }

    @Override
    public TeacherResources FindResourceByTeacherEmail(String code, String email) {
        return repo.FindResourcesByTeacherEmail(code, email);
    }

    @Override
//    @GraphQLQuery(name = "getTeacherResources")
    public List<TeacherResources> data(@GraphQLArgument(name = "search") String search,
                                       @GraphQLArgument(name = "page") int page,
                                       @GraphQLArgument(name = "status") int status) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<TeacherResources> pages = repo.Resources(search, 2, pageable);
        totalElements = pages.getTotalElements();
        totalPages = pages.getTotalPages();
        currentPages = page;
        return repo.findAll();
    }

    @Override
    public TeacherResources save(TeacherResources teacherResources) {
        try {
            repo.save(teacherResources);
        } catch (Exception e) {
            return null;
        }
        return teacherResources;
    }

    @Override
    public boolean deleteById(String id) {
        repo.deleteById(id);

        return true;
    }

    @Override
    public TeacherResources findById(String id) {
        Optional<TeacherResources> resources = repo.findById(id);

        return resources.orElse(null);
    }

    @Override
    public ApiSettings apiSettings() {
        return new ApiSettings(totalElements, totalPages, currentPages);
    }


    @Override
    public long count() {

        // not customize
        return repo.count();
    }
}
