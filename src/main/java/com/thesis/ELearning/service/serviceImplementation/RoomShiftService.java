package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.API.ApiSettings;
import com.thesis.ELearning.entity.DashBoard;
import com.thesis.ELearning.entity.RoomShift;
import com.thesis.ELearning.entity.Student;
import com.thesis.ELearning.repository.DashboardRepository;
import com.thesis.ELearning.repository.RoomShiftRepository;
import com.thesis.ELearning.repository.StudentRepository;
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
    final private StudentRepository studentRepository;
    private final DashBoard dashBoard;
    private final DashboardRepository dashboardRepository;
    private int totalPages = 0;
    private long totalElements = 0;
    private int currentPages = 0;

    @Autowired
    public RoomShiftService(RoomShiftRepository repo, StudentRepository studentRepository, DashboardRepository dashboardRepository) {
        this.studentRepository = studentRepository;
        this.repo = repo;
        this.dashboardRepository = dashboardRepository;

        dashBoard = dashboardRepository.findById(1).orElse(null);
    }

    @Override
    @GraphQLQuery(name = "roomShifts")
    public List<RoomShift> data(@GraphQLArgument(name = "search") String search, @GraphQLArgument(name = "page") int page) {
        Pageable pageable = PageRequest.of(page,10);
        Page<RoomShift> pages = repo.RoomShift(search, pageable);
        totalElements =  pages.getTotalElements();
        totalPages = pages.getTotalPages();
        currentPages = page;
        return pages.getContent();
    }

    @Override
    public boolean deleteById(String id) {

        try {
            repo.deleteById(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    @GraphQLQuery(name = "roomShift")
    public RoomShift findById(@GraphQLArgument(name = "id") String id) {
        Optional<RoomShift> roomShift = repo.findById(id);
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


    @GraphQLQuery(name = "getStudentsForRoomShift")
    public List<Student> getStudentsForRoomShift(@GraphQLArgument(name = "search") String search){
        Pageable pageable = PageRequest.of(0,20);
        Page<Student> studentPage = studentRepository.getStudentForRoomShift(search,pageable);
        return studentPage.getContent();
    }

    @GraphQLQuery(name = "uploadStudentsInRoomShift")
    public RoomShift roomShift(@GraphQLArgument(name = "roomShiftID") String id, @GraphQLArgument(name = "students") String array ){
        Optional<RoomShift> find = repo.findById(id);

        if(find.isPresent()){
            RoomShift roomShift = find.get();
//            roomShift.setStudents(students);

            return  roomShift;
        }



        return null;
    }

    @Override
    public long count() {
        return repo.count();
    }
}
