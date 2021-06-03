package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.TeacherLectures;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherLectureRepository extends JpaRepository<TeacherLectures, String> {
    @Query(value = "SELECT t from TeacherLectures t where (t.code like %?1%) or t.resources.teacher.user.email =?2 order by t.createdAt desc ")
    Page<TeacherLectures> getTeacherLecture(String search, String email , Pageable pageable);

    @Query(value = "SELECT t from TeacherLectures  t where t.code = ?1 and t.resources.teacher.user.email = ?2")
    TeacherLectures getTeacherLecturesByCodeAndEmail(String code, String email);

}
