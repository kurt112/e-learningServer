
package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.API.ApiSettings;
import com.thesis.ELearning.entity.DashBoard;
import com.thesis.ELearning.repository.DashboardRepository;
import com.thesis.ELearning.service.PageableService.PageableServiceDashBoard;
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

/**
 * @Author Kurt Orioque
 */

@Transactional
@Service
@GraphQLApi
public class DashboardService implements PageableServiceDashBoard {

    private final DashboardRepository repo;
    private int totalPages = 0;
    private long totalElements = 0;
    private int currentPages = 0;

    @Autowired
    public DashboardService(DashboardRepository repo) {
        this.repo = repo;
    }

    @Override
    @GraphQLQuery(name = "getDashboard")
    public List<DashBoard> data(@GraphQLArgument(name = "search") String search, @GraphQLArgument(name = "page") int page) {
        Pageable pageable = PageRequest.of(page,10);
        Page<DashBoard> pages = repo.findAll(pageable);
        totalElements =  pages.getTotalElements();
        totalPages = pages.getTotalPages();
        currentPages = page;
        return repo.findAll();
    }

    @Override
    public DashBoard save(DashBoard dashBoard) {
        repo.save(dashBoard);
        return dashBoard;
    }

    @Override
    public boolean deleteById(String id) {
        try {
            repo.deleteById(Integer.parseInt(id));
        }catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    @GraphQLQuery(name = "getDashBoard")
    public DashBoard findById(@GraphQLArgument(name = "id") String id) {
        Optional<DashBoard> dashBoard = repo.findById(Integer.parseInt(id));
        return dashBoard.orElse(null);
    }

    @Override
    @GraphQLQuery(name = "dashboardSettings")
    public ApiSettings apiSettings() {
        return new ApiSettings(totalElements, totalPages, currentPages);
    }

    @Override
    public long count() {
        return repo.count();
    }
}
