package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.API.ApiSettings;
import com.thesis.ELearning.entity.RoomShiftClass;
import com.thesis.ELearning.repository.RoomShiftClassesRepository;
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
public class RoomShiftClassesService implements PageableServiceRoomShiftClass {

    final private RoomShiftClassesRepository repo;
    private int totalPages = 0;
    private long totalElements = 0;
    private int currentPages = 0;
    @Autowired
    public RoomShiftClassesService(RoomShiftClassesRepository repo) {
        this.repo = repo;
    }


    @Override
    @GraphQLQuery(name = "roomShiftClasses")
    public List<RoomShiftClass> data(@GraphQLArgument(name = "search") String search, @GraphQLArgument(name = "page") int page) {
        Pageable pageable = PageRequest.of(page,10);
        Page<RoomShiftClass> pages = repo.RoomClasses(search, pageable);
        totalElements =  pages.getTotalElements();
        totalPages = pages.getTotalPages();
        currentPages = page;
        return pages.getContent();
    }

    @Override
    public RoomShiftClass save(RoomShiftClass roomShiftClasses) {
        try{
            repo.save(roomShiftClasses);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return roomShiftClasses;
    }

    @Override
    public boolean deleteById(String id) {
        try{
            repo.deleteById(id);
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    @GraphQLQuery(name = "roomShiftClass")
    public RoomShiftClass findById(@GraphQLArgument(name = "id") String id) {
        Optional<RoomShiftClass> roomClasses = repo.findById(id);

        return roomClasses.orElse(null);
    }

    @Override
    @GraphQLQuery(name = "roomClassesSettings")
    public ApiSettings apiSettings() {
        return new ApiSettings(totalElements,totalPages,currentPages);
    }

    public RoomShiftClass FindRoomClassByShiftAndSubject(int shiftId, String subjectCode){
        RoomShiftClass roomShiftClasses = repo.FindRoomClassByShiftAndSubject(shiftId,subjectCode);

        return roomShiftClasses;
    }

    @Override
    public void deleteRoomShiftClass(String id) {

        try {
            repo.deleteRoomShiftClass(id);
        }catch (Exception e){
            System.out.println(e);
            return;
        }
    }
}
