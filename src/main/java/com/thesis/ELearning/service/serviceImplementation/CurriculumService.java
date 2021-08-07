package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.API.ApiSettings;
import com.thesis.ELearning.entity.Curriculum;
import com.thesis.ELearning.repository.CurriculumRepository;
import com.thesis.ELearning.service.PageableService.PageableServiceCurriculum;
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
public class CurriculumService implements PageableServiceCurriculum {

    private final CurriculumRepository repo;
    private int totalPages = 0;
    private long totalElements = 0;
    private int currentPages = 0;

    @Autowired
    public CurriculumService(CurriculumRepository repo) {
        this.repo = repo;
    }

    @Override
    @GraphQLQuery(name = "curriculums")
    public List<Curriculum> data(@GraphQLArgument(name = "search") String search, @GraphQLArgument(name = "page") int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Curriculum> pages = repo.curriculums(search, pageable);
        totalElements = pages.getTotalElements();
        totalPages = pages.getTotalPages();
        currentPages = page;
        return pages.getContent();
    }

    @Override
    public Curriculum save(Curriculum curriculum) {
        repo.save(curriculum);
        return curriculum;
    }

    @Override
    public boolean deleteById(String id) {
        repo.deleteById(id);
        return true;
    }

    @Override
    @GraphQLQuery(name = "getCurriculum")
    public Curriculum findById(@GraphQLArgument(name = "id") String id) {
        Optional<Curriculum> curriculum = repo.findById(id);
        return curriculum.orElse(null);
    }

    @Override
    @GraphQLQuery(name = "curriculumSettings")
    public ApiSettings apiSettings() {

        return new ApiSettings(totalElements, totalPages, currentPages);
    }

    @Override
    public long count() {
        return repo.count();
    }
}
