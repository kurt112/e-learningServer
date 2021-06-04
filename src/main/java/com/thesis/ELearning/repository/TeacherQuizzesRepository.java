package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.TeacherQuizzes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherQuizzesRepository extends JpaRepository<TeacherQuizzes, String> {
    @Query(value = "SELECT t FROM TeacherQuizzes t where t.roomShiftClass.teacher.user.email = ?2 and " +
            "t.resource.name like %?1% or " +
            "t.code like %?1% or " +
            "t.description like %?1% order by t.createdAt desc ")
    Page<TeacherQuizzes> getTeacherQuizzes(String search, String email , Pageable pageable);

    @Query(value = "SELECT t from TeacherQuizzes t where t.code = ?1 and t.roomShiftClass.teacher.user.email =?2")
    TeacherQuizzes getTeacherQuizzesByCodeAndEmail(String code, String email);

}
