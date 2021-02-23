package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.API.ApiSettings;
import com.thesis.ELearning.entity.Room;
import com.thesis.ELearning.entity.Student;
import com.thesis.ELearning.repository.RoomRepository;
import com.thesis.ELearning.service.PagableParentClass.ServicePageable;
import com.thesis.ELearning.service.PageableService.PageableServiceRoom;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@GraphQLApi
public class RoomService implements PageableServiceRoom {

    final private RoomRepository repo;
    private int totalPages = 0;
    private long totalElements = 0;
    private int currentPages = 0;
    @Autowired
    public RoomService(RoomRepository repo) {
        this.repo = repo;
    }

    @Override
    @GraphQLQuery(name = "rooms")
    public List<Room> data(@GraphQLArgument(name = "search") String search,@GraphQLArgument(name = "page") int page) {
        Pageable pageable = PageRequest.of(page,10);
        Page<Room> pages = repo.Rooms(search, pageable);
        totalElements =  pages.getTotalElements();
        totalPages = pages.getTotalPages();
        currentPages = page;
        return pages.getContent();
    }

    @Override
    public Room save(Room o) {
        try {
            repo.save(o);
        }catch (Exception e){
            return null;
        }
        return o;
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
    @GraphQLQuery(name = "room")
    public Room findById(@GraphQLArgument(name = "id")String id)  {
        Optional<Room> room = repo.findById(id);
        return room.orElse(null);
    }

    @Override
    @GraphQLQuery(name = "roomSettings")
    public ApiSettings apiSettings() {

        return new ApiSettings(totalElements, totalPages, currentPages);
    }

}
