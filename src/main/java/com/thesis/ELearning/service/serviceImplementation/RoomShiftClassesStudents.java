package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.API.ApiSettings;
import com.thesis.ELearning.entity.RoomShiftClassStudents;
import com.thesis.ELearning.repository.RoomShiftClassesStudent;
import com.thesis.ELearning.service.PageableService.PageableServiceRoomShiftClassStudent;
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
public class RoomShiftClassesStudents implements PageableServiceRoomShiftClassStudent {

    private final RoomShiftClassesStudent repo;
    private int totalPages = 0;
    private long totalElements = 0;
    private int currentPages = 0;
    @Autowired
    public RoomShiftClassesStudents(RoomShiftClassesStudent repo) {
        this.repo = repo;
    }

    @Override
    public List<RoomShiftClassStudents> data(String search, int page) {
        Pageable pageable = PageRequest.of(page,10);
//        Page<RoomClass> pages = repo.RoomClasses(search, pageable);
//        totalElements =  pages.getTotalElements();
//        totalPages = pages.getTotalPages();
//        currentPages = page;
        return null;
    }

    @Override
    public RoomShiftClassStudents save(RoomShiftClassStudents roomClassesStudents) {
        try{
            repo.save(roomClassesStudents);
        }catch (Exception e){
            return null;
        }
        return roomClassesStudents;
    }

    @Override
    public boolean deleteById(String id) {
        try{
            repo.deleteById(Integer.parseInt(id));
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public RoomShiftClassStudents findById(String id) {
        Optional<RoomShiftClassStudents> roomClasses = repo.findById(Integer.parseInt(id));

        return roomClasses.orElse(null);
    }

    @Override
    public ApiSettings apiSettings() {
        return new ApiSettings(totalElements,totalPages,currentPages);
    }


    @GraphQLQuery(name = "studentInRoomClass")
    public List<RoomShiftClassStudents> studentInRoomClass(@GraphQLArgument(name = "search") String search,
                                                           @GraphQLArgument(name = "classId")  String classId){
        Pageable pageable = PageRequest.of(0,10);
//        Page<RoomShiftClassStudents> pages = repo.studentInRoomClass(search.replaceAll("\\s".toLowerCase(),""),classId, pageable);


        return null;// pages.getContent();
    }

    @GraphQLQuery(name = "studentNotInRoomClass")
    public List<RoomShiftClassStudents> studentNotInRoomClass(@GraphQLArgument(name = "search") String search,
                                                              @GraphQLArgument(name = "classId")  String classId){
        Pageable pageable = PageRequest.of(0,10);
//        Page<RoomShiftClassStudents> pages = repo.studentNotInRoomClass(search.replaceAll("\\s".toLowerCase(),""),classId, pageable);


        return null;//pages.getContent();
    }


}
