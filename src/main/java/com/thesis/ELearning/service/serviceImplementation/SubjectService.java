package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.API.ApiSettings;
import com.thesis.ELearning.entity.Subject;
import com.thesis.ELearning.repository.SubjectRepository;
import com.thesis.ELearning.service.PageableService.PageableServiceSubject;
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
public class SubjectService implements PageableServiceSubject {

    final private SubjectRepository repo;
    private int totalPages = 0;
    private long totalElements = 0;
    private int currentPages = 0;

    @Autowired
    public SubjectService(SubjectRepository repo) {
        this.repo = repo;
    }

    @Override
    @GraphQLQuery(name = "subjects")
    public List<Subject> data(@GraphQLArgument(name = "search") String search,
                              @GraphQLArgument(name = "page") int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Subject> pages = repo.subjects(search, pageable);
        totalElements = pages.getTotalElements();
        totalPages = pages.getTotalPages();
        currentPages = page;


        return pages.getContent();
    }


    @Override
    @GraphQLQuery(name = "searchSubject")
    public List<Subject> searchSubjectByNameAndCode(@GraphQLArgument(name = "search") String search,
                                                    @GraphQLArgument(name = "page") int page) {
        Pageable pageable = PageRequest.of(page, 10);
        System.out.println(search.replaceAll("\\s", ""));
        return repo.searchSubjectByNameAndCode(search, pageable).getContent();
    }

    @Override
    public Subject save(Subject subject) {
        try {
            repo.save(subject);
        } catch (Exception e) {
            return null;
        }
        return subject;
    }

    @Override
    public boolean deleteById(String id) {
        try {
            repo.deleteById(Integer.parseInt(id));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Subject findByIdIndex(int id) {

        Optional<Subject> subject = repo.findById(id);

        return subject.orElse(null);
    }

    @Override
    @GraphQLQuery(name = "getSubject")
    public Subject findById(@GraphQLArgument(name = "code") String code) {


        return repo.findSubjectBySubjectCode(code);
    }

    @Override
    @GraphQLQuery(name = "subjectSettings")
    public ApiSettings apiSettings() {
        return new ApiSettings(totalElements, totalPages, currentPages);
    }
}
