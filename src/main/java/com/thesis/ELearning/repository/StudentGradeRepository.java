package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.StudentGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentGradeRepository extends JpaRepository<StudentGrade,Integer> {

    @Query(value = "SELECT t from StudentGrade t where t.roomShiftClass.id = ?1 and t.student.id = ?2")
    StudentGrade getStudentGrade(String classId, String studentId);
}