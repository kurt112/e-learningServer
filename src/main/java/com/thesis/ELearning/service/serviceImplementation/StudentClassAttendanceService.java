package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.API.ApiSettings;
import com.thesis.ELearning.entity.StudentClassAttendance;
import com.thesis.ELearning.repository.StudentClassAttendanceRepository;
import com.thesis.ELearning.service.PageableService.PageableServiceStudentClassAttendance;
import com.thesis.ELearning.service.ServicesIntegerId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class StudentClassAttendanceService implements PageableServiceStudentClassAttendance {

    final private StudentClassAttendanceRepository repo;

    @Autowired
    public StudentClassAttendanceService(StudentClassAttendanceRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<StudentClassAttendance> data(String search, int page) {
        return null;
    }

    @Override
    public StudentClassAttendance save(StudentClassAttendance studentClassAttendance) {
        repo.save(studentClassAttendance);
        return studentClassAttendance;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }

    @Override
    public StudentClassAttendance findById(String id) {
        return null;
    }

    @Override
    public long countByClass(String classId) {
        return repo.countAttendanceByClass(classId);
    }

    @Override
    public ApiSettings apiSettings() {
        return null;
    }

    @Override
    public StudentClassAttendance getLast() {
        return repo.findTopByOrderByIdDesc();
    }

    @Override
    public long count() {
        return 0;
    }
}
