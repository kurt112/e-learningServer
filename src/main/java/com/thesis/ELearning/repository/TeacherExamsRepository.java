package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.TeacherExams;
import com.thesis.ELearning.entity.TeacherLectures;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherExamsRepository extends JpaRepository<TeacherExams, String> {
    @Query(value = "SELECT t FROM TeacherExams t where t.roomShiftClass.teacher.user.email = ?2 and " +
            "t.resource.name like %?1% or " +
            "t.code like %?1% or " +
            "t.description like %?1% order by t.createdAt desc ")
    Page<TeacherExams> getTeacherExams(String search, String email , Pageable pageable);

    @Query(value = "SELECT t from TeacherExams t where t.code = ?1 and t.roomShiftClass.teacher.user.email =?2")
    TeacherExams getTeacherExamsByCodeAndEmail(String code, String email);

}
