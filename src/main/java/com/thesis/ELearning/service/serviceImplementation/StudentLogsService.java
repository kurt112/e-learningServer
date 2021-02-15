package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.StudentLogs;
import com.thesis.ELearning.repository.StudentLogsRepository;
import com.thesis.ELearning.service.ServicesIntegerId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class StudentLogsService implements ServicesIntegerId<StudentLogs> {

    final private StudentLogsRepository repo;

    @Autowired
    public StudentLogsService(StudentLogsRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<StudentLogs> findAll() {
        return repo.findAll();
    }

    @Override
    public StudentLogs findById(int id) {
        Optional<StudentLogs> logs = repo.findById(id);
        return logs.orElse(null);
    }

    @Override
    public boolean save(StudentLogs studentLogs) {
        try {
            repo.save(studentLogs);
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
    public boolean delete(StudentLogs studentLogs) {
        try {
            repo.delete(studentLogs);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
