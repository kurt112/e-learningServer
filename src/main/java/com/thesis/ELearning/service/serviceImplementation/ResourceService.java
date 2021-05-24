package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.API.ApiSettings;
import com.thesis.ELearning.entity.Resources;
import com.thesis.ELearning.repository.ResourceRepository;
import com.thesis.ELearning.service.PageableService.PageableServiceResources;
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
public class ResourceService implements PageableServiceResources {

    private final ResourceRepository repo;
    private int totalPages = 0;
    private long totalElements = 0;
    private int currentPages = 0;

    @Autowired
    public ResourceService(ResourceRepository repo) {
        this.repo = repo;
    }

    @Override
//    @GraphQLQuery(name = "getTeacherResources")
    public List<Resources> data(@GraphQLArgument(name = "search") String search,
                                @GraphQLArgument(name = "page") int page) {
        Pageable pageable = PageRequest.of(page,10);
        Page<Resources> pages = repo.Resources(search, 2, pageable);
        totalElements =  pages.getTotalElements();
        totalPages = pages.getTotalPages();
        currentPages = page;
        return repo.findAll();
    }

    @Override
    public Resources save(Resources resources) {
        try {
            repo.save(resources);
        }catch (Exception e){
            return null;
        }
        return resources;
    }

    @Override
    public boolean deleteById(String id) {
        try{
            repo.deleteById(id);
        }catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public Resources findById(String id) {
        Optional<Resources> resources = repo.findById(id);

        return resources.orElse(null);
    }

    @Override
    public ApiSettings apiSettings() {
        return new ApiSettings(totalElements, totalPages, currentPages);
    }
}
