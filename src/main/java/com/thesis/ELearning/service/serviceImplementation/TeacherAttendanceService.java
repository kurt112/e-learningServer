package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.TeacherAttendance;
import com.thesis.ELearning.repository.TeacherAttendanceRepository;
import com.thesis.ELearning.service.ServicesIntegerId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class TeacherAttendanceService implements ServicesIntegerId<TeacherAttendance> {
    final private TeacherAttendanceRepository repo;

    @Autowired
    public TeacherAttendanceService(TeacherAttendanceRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<TeacherAttendance> findAll() {
        return repo.findAll();
    }

    @Override
    public TeacherAttendance findById(int id) {
        Optional<TeacherAttendance> teacherID = repo.findById(id);
        return teacherID.orElse(null);
    }

    @Override
    public boolean save(TeacherAttendance teacherAttendance) {
        try {
            repo.save(teacherAttendance);
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
    public boolean delete(TeacherAttendance teacherAttendance) {
        try {
            repo.delete(teacherAttendance);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
