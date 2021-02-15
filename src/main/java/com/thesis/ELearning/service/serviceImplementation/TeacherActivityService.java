package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.TeacherActivity;
import com.thesis.ELearning.repository.TeacherActivityRepository;
import com.thesis.ELearning.service.ServicesIntegerId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class TeacherActivityService implements ServicesIntegerId<TeacherActivity> {

    final private TeacherActivityRepository repo;

    @Autowired
    public TeacherActivityService(TeacherActivityRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<TeacherActivity> findAll() {
        return repo.findAll();
    }

    @Override
    public TeacherActivity findById(int id) {
        Optional<TeacherActivity> teacherActivity = repo.findById(id);
        return teacherActivity.orElse(null);
    }

    @Override
    public boolean save(TeacherActivity teacherActivity) {
        try {
            repo.save(teacherActivity);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            repo.deleteById(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(TeacherActivity teacherActivity) {
        try {
            repo.delete(teacherActivity);
        }catch (Exception e){
            return false;
        }
        return false;
    }
}
