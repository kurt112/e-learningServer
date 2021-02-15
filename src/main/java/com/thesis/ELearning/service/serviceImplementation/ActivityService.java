package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.Activity;
import com.thesis.ELearning.repository.ActivityRepository;
import com.thesis.ELearning.service.PagableParentClass.ServicePageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
public class ActivityService implements ServicePageable<Activity> {

    final private ActivityRepository repo;

    @Autowired
    public ActivityService(ActivityRepository repo) {
        this.repo = repo;
    }

    @Override
    public Page<Activity> data(String search, int page) {
        Pageable pageable = PageRequest.of(page,10);
        return null;
    }

    @Override
    public boolean save(Activity activity) {
        try {
            repo.save(activity);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteById(String id) {
        try {
            repo.deleteById(Integer.parseInt(id));
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Activity findById(String id) {
        Optional<Activity> activity = repo.findById(Integer.parseInt(id));
        return activity.orElse(null);
    }
}