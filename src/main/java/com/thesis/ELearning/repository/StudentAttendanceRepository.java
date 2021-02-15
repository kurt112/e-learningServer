package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.StudentAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAttendanceRepository extends JpaRepository<StudentAttendance, Integer> {
}
