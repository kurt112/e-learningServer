package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.TeacherAssignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherAssignmentRepository extends JpaRepository<TeacherAssignment, String> {

    @Query(value = "SELECT t FROM TeacherAssignment t where t.resource.name like %?1% or " +
            "t.resource.description like %?1% ORDER BY t.createdAt DESC")
    Page<TeacherAssignment> data(String search, Pageable pageable);


    @Query(value = "SELECT t FROM TeacherAssignment t where t.roomShiftClass.teacher.user.email = ?2 and (" +
            "t.resource.name like %?1% or " +
            "t.code like %?1% or " +
            "t.description like %?1%) order by t.createdAt desc ")
    Page<TeacherAssignment> getRoomShiftClassAssignmentByTeacher(String search, String email, Pageable pageable);

    @Query(value = "SELECT t from TeacherAssignment t where t.code = ?1 and t.roomShiftClass.teacher.user.email =?2")
    TeacherAssignment getRoomShiftClassAssignment(String code, String email);

}
