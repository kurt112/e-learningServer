package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.StudentClassAttendance;
import com.thesis.ELearning.entity.TeacherClassAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface TeacherClassAttendanceRepository extends JpaRepository<TeacherClassAttendance, Integer> {
    @Query(value = "SELECT COUNT(t) FROM TeacherClassAttendance t WHERE t.teacher_class.id = ?1")
    long countAttendanceByClass(String classId);
    TeacherClassAttendance findTopByOrderByIdDesc();
}
