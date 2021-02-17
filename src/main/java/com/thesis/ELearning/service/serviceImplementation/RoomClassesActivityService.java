package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.API.ApiSettings;
import com.thesis.ELearning.entity.RoomClassesActivity;
import com.thesis.ELearning.repository.RoomClassesActivityRepository;
import com.thesis.ELearning.service.PageableService.PageableServiceRoomClassesActivity;
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
public class RoomClassesActivityService implements PageableServiceRoomClassesActivity {

    private final RoomClassesActivityRepository repo;
    private int totalPages = 0;
    private long totalElements = 0;
    private int currentPages = 0;

    @Autowired
    public RoomClassesActivityService(RoomClassesActivityRepository repo) {
        this.repo = repo;
    }


    @Override
    @GraphQLQuery(name = "activities")
    public List<RoomClassesActivity> data(@GraphQLArgument(name = "search") String search, @GraphQLArgument(name = "page") int page) {
        Pageable pageable = PageRequest.of(page,10);
        Page<RoomClassesActivity> pages = repo.RoomClassesActivity(search, pageable);
        totalElements =  pages.getTotalElements();
        totalPages = pages.getTotalPages();
        currentPages = page;
        return pages.getContent();
    }

    @Override
    public RoomClassesActivity save(RoomClassesActivity roomClassesActivity) {
        try {
            repo.save(roomClassesActivity);
        }catch (Exception e){
            return null;
        }

        return roomClassesActivity;
    }

    @Override
    public boolean deleteById(String id) {

        try {
            repo.deleteById(Integer.parseInt(id));
        }catch (Exception e){
           return false;
        }
        return true;
    }

    @Override
    public RoomClassesActivity findById(String id) {
        Optional<RoomClassesActivity> roomClassesActivity = repo.findById(Integer.parseInt(id));

        return roomClassesActivity.orElse(null);
    }

    @Override
    @GraphQLQuery(name = "activitySettings")
    public ApiSettings apiSettings() {
        return new ApiSettings(totalElements, totalPages, currentPages);
    }
}
