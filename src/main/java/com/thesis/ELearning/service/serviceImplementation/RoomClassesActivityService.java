package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.RoomClassesActivity;
import com.thesis.ELearning.repository.RoomClassesActivityRepository;
import com.thesis.ELearning.service.PageableService.PageableServiceRoomClassesActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
@Transactional
@Service
public class RoomClassesActivityService implements PageableServiceRoomClassesActivity {

    private final RoomClassesActivityRepository repo;

    @Autowired
    public RoomClassesActivityService(RoomClassesActivityRepository repo) {
        this.repo = repo;
    }


    @Override
    public Page<RoomClassesActivity> data(String search, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        System.out.println("The search " + search);
        return repo.RoomClassesActivity(search,pageable);
    }

    @Override
    public boolean save(RoomClassesActivity roomClassesActivity) {
        try {
            repo.save(roomClassesActivity);
        }catch (Exception e){
            return false;
        }

        return true;
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
}
