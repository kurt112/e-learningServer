package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.StudentClassAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentClassAttendanceRepository extends JpaRepository<StudentClassAttendance, Integer> {

    @Query(value = "SELECT COUNT(t) FROM StudentClassAttendance t WHERE t.student_class.id = ?1")
    long countAttendanceByClass(String classId);

    StudentClassAttendance findTopByOrderByIdDesc();

}
