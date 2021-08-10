package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.API.ApiSettings;
import com.thesis.ELearning.entity.TeacherClassAttendance;
import com.thesis.ELearning.repository.TeacherClassAttendanceRepository;
import com.thesis.ELearning.service.PageableService.PageableServiceTeacherClassAttendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class TeacherClassAttendanceService implements PageableServiceTeacherClassAttendance {

    final private TeacherClassAttendanceRepository repo;

    @Autowired
    public TeacherClassAttendanceService(TeacherClassAttendanceRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<TeacherClassAttendance> data(String search, int page) {
        return repo.findAll();
    }

    @Override
    public TeacherClassAttendance save(TeacherClassAttendance teacherClassAttendance) {
        repo.save(teacherClassAttendance);
        return teacherClassAttendance;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }

    @Override
    public TeacherClassAttendance findById(String id) {
        return null;
    }

    @Override
    public long countByClass(String classId) {
        return repo.countAttendanceByClass(classId);
    }

    @Override
    public TeacherClassAttendance getLast() {
        return repo.findTopByOrderByIdDesc();
    }

    @Override
    public ApiSettings apiSettings() {
        return null;
    }


    @Override
    public long count() {
        return 0;
    }
}
