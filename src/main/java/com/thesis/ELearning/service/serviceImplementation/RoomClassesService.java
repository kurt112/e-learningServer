package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.API.ApiSettings;
import com.thesis.ELearning.entity.RoomClass;
import com.thesis.ELearning.repository.RoomClassesRepository;
import com.thesis.ELearning.service.PageableService.PageableServiceRoomShiftClass;
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
public class RoomClassesService implements PageableServiceRoomShiftClass {

    final private RoomClassesRepository repo;
    private int totalPages = 0;
    private long totalElements = 0;
    private int currentPages = 0;
    @Autowired
    public RoomClassesService(RoomClassesRepository repo) {
        this.repo = repo;
    }


    @Override
    @GraphQLQuery(name = "roomShiftClasses")
    public List<RoomClass> data(@GraphQLArgument(name = "search") String search, @GraphQLArgument(name = "page") int page) {
        Pageable pageable = PageRequest.of(page,10);
        Page<RoomClass> pages = repo.RoomClasses(search, pageable);
        totalElements =  pages.getTotalElements();
        totalPages = pages.getTotalPages();
        currentPages = page;
        return pages.getContent();
    }

    @Override
    public RoomClass save(RoomClass roomClasses) {
        try{
            repo.save(roomClasses);
        }catch (Exception e){
            return null;
        }
        return roomClasses;
    }

    @Override
    public boolean deleteById(String id) {
        try{
            repo.deleteById(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    @GraphQLQuery(name = "roomShiftClass")
    public RoomClass findById(@GraphQLArgument(name = "id") String id) {
        Optional<RoomClass> roomClasses = repo.findById(id);

        return roomClasses.orElse(null);
    }

    @Override
    @GraphQLQuery(name = "roomClassesSettings")
    public ApiSettings apiSettings() {
        return new ApiSettings(totalElements,totalPages,currentPages);
    }

    public RoomClass FindRoomClassByShiftAndSubject(int shiftId, String subjectCode){
        RoomClass roomClasses = repo.FindRoomClassByShiftAndSubject(shiftId,subjectCode);

        return roomClasses;
    }

}
