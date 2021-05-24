package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.API.ApiSettings;
import com.thesis.ELearning.entity.RoomShiftClassAssignment;
import com.thesis.ELearning.repository.RoomShiftClassesAssignmentRepository;
import com.thesis.ELearning.service.PageableService.PageableServiceRoomShiftClassAssignment;
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

@Transactional
@Service
@GraphQLApi
public class RoomShiftClassesAssignmentService implements PageableServiceRoomShiftClassAssignment {

    private final RoomShiftClassesAssignmentRepository repo;
    private int totalPages = 0;
    private long totalElements = 0;
    private int currentPages = 0;

    @Autowired
    public RoomShiftClassesAssignmentService(RoomShiftClassesAssignmentRepository repo) {
        this.repo = repo;
    }

    @GraphQLQuery(name = "getRoomShiftClassAssignmentByTeacher")
    public List<RoomShiftClassAssignment> getRoomShiftClassAssignmentByTeacher(@GraphQLArgument(name = "search") String search,@GraphQLArgument(name = "email") String email, @GraphQLArgument(name = "page") int page){
        System.out.println(email);
        Pageable pageable = PageRequest.of(page, 10);
        Page<RoomShiftClassAssignment> pages = repo.getRoomShiftClassAssignmentByTeacher(search,email, pageable);
        totalElements = pages.getTotalElements();
        totalPages = pages.getTotalPages();
        currentPages = page;
        return pages.getContent();
    }


    @Override
    public List<RoomShiftClassAssignment> data(@GraphQLArgument(name = "search") String search, @GraphQLArgument(name = "page") int page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<RoomShiftClassAssignment> pages = repo.data(search, pageable);
        totalElements = pages.getTotalElements();
        totalPages = pages.getTotalPages();
        currentPages = page;
        return pages.getContent();
    }

    @Override
    public RoomShiftClassAssignment save(RoomShiftClassAssignment roomShiftClassAssignment) {
        return null;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }

    @Override
    public RoomShiftClassAssignment findById(String id) {
        return null;
    }

    @Override
    @GraphQLQuery(name = "roomShiftClassAssignmentSettings")
    public ApiSettings apiSettings() {
        return new ApiSettings(totalElements, totalPages, currentPages);
    }
}
