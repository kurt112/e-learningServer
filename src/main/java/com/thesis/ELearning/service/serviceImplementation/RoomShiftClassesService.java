package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.API.ApiSettings;
import com.thesis.ELearning.entity.DashBoard;
import com.thesis.ELearning.entity.RoomShiftClass;
import com.thesis.ELearning.repository.DashboardRepository;
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
    private final DashBoard dashBoard;
    private final DashboardRepository dashboardRepository;
    private int totalPages = 0;
    private long totalElements = 0;
    private int currentPages = 0;
    @Autowired
    public RoomShiftClassesService(RoomShiftClassesRepository repo, DashboardRepository dashboardRepository) {
        this.repo = repo;
        this.dashboardRepository = dashboardRepository;

        dashBoard = dashboardRepository.findById(1).orElse(null);
    }


    @Override
    @GraphQLQuery(name = "roomShiftClasses")
    public List<RoomShiftClass> data(@GraphQLArgument(name = "search") String search,
                                     @GraphQLArgument(name = "page") int page,
                                     @GraphQLArgument(name= "status") int status) {
        Pageable pageable = PageRequest.of(page,10);
        Page<RoomShiftClass> pages;

        System.out.println("The status " + status);

        if(status ==2){
           pages = repo.RoomClasses(search, pageable);
        } else pages = repo.RoomClasses(search,status, pageable);
        totalElements =  pages.getTotalElements();
        totalPages = pages.getTotalPages();
        currentPages = page;
        return pages.getContent();
    }

    @Override
    public RoomShiftClass save(RoomShiftClass roomShiftClasses) {
        try{
            if(repo.findById(roomShiftClasses.getId()).isEmpty())
                dashboardRepository.save(dashBoard.IncClassCount());
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
            dashboardRepository.save(dashBoard.DecClassCount());
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

        System.out.println("asdasd");
        System.out.println(roomClasses);

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

    @Override
    public long count() {
        return repo.count();
    }
}
