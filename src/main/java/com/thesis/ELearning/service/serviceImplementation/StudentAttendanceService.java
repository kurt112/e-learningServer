package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.StudentAttendance;
import com.thesis.ELearning.repository.StudentAttendanceRepository;
import com.thesis.ELearning.service.ServicesIntegerId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class StudentAttendanceService implements ServicesIntegerId<StudentAttendance>{

    final private StudentAttendanceRepository repo;

    @Autowired
    public StudentAttendanceService(StudentAttendanceRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<StudentAttendance> findAll() {
        return repo.findAll();
    }

    @Override
    public StudentAttendance findById(int id) {

        Optional<StudentAttendance> studentAttendance = repo.findById(id);

        return studentAttendance.orElse(null);
    }

    @Override
    public boolean save(StudentAttendance studentAttendance) {
        try {
            repo.save(studentAttendance);
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
    public boolean delete(StudentAttendance studentAttendance) {
        try {
            repo.delete(studentAttendance);
        }catch (Exception e){
            return false;
        }
        return true;
    }


}
