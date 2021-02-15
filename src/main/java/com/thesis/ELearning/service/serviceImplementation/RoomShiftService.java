package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.API.ApiSettings;
import com.thesis.ELearning.entity.RoomShift;
import com.thesis.ELearning.repository.RoomShiftRepository;
import com.thesis.ELearning.service.PageableService.PageableServiceRoomShift;
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
public class RoomShiftService implements PageableServiceRoomShift {

    final private RoomShiftRepository repo;

    private int totalPages = 0;
    private long totalElements = 0;
    private int currentPages = 0;

    @Autowired
    public RoomShiftService(RoomShiftRepository repo) {
        this.repo = repo;
    }

    @Override
    @GraphQLQuery(name = "roomShifts")
    public List<RoomShift> data(@GraphQLArgument(name = "search") String search, @GraphQLArgument(name = "page") int page) {
        Pageable pageable = PageRequest.of(page,10);
        Page<RoomShift> pages = repo.RoomShift(search, pageable);
        totalElements =  pages.getTotalElements();
        totalPages = pages.getTotalPages();
        currentPages = page;
        System.out.println("The totla pages " + totalPages);
        return pages.getContent();
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
    public RoomShift findById(String id) {
        Optional<RoomShift> roomShift = repo.findById(Integer.parseInt(id));
        return roomShift.orElse(null);
    }

    @Override
    @GraphQLQuery()
    public RoomShift  save(RoomShift roomShift) {

         try {
             repo.save(roomShift);
         }catch (Exception e){
             return null;
         }

         return roomShift;
    }

    @Override
    @GraphQLQuery(name = "roomShiftsSettings")
    public ApiSettings apiSettings() {
        return new ApiSettings(totalElements, totalPages, currentPages);
    }
}
