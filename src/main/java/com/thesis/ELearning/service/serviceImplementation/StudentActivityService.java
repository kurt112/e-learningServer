package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.Student;
import com.thesis.ELearning.entity.StudentActivity;
import com.thesis.ELearning.repository.StudentActivityRepository;
import com.thesis.ELearning.repository.StudentRepository;
import com.thesis.ELearning.service.ServicesIntegerId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class StudentActivityService implements ServicesIntegerId<StudentActivity> {

    final private StudentActivityRepository repo;

    @Autowired
    public StudentActivityService(StudentActivityRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<StudentActivity> findAll() {
        return repo.findAll();
    }

    @Override
    public StudentActivity findById(int id) {

        Optional<StudentActivity> student = repo.findById(id);


        return student.orElse(null);
    }

    @Override
    public boolean save(StudentActivity student) {
        try {
            repo.save(student);
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
    public boolean delete(StudentActivity student) {
        try {
            repo.save(student);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
