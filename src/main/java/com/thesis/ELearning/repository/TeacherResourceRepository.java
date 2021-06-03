package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.TeacherResources;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherResourceRepository extends JpaRepository<TeacherResources, String> {
    @Query(value = "SELECT t from TeacherResources t where ("+
            "t.code like  %?1% or t.name like %?1% or t.type like %?1% or t.status like %?1%"+") order by t.createdAt desc")
    Page<TeacherResources> Resources(String search, int id, Pageable pageable);

    @Query(value = "SELECT t from TeacherResources t where t.code = ?1 and t.teacher.user.email = ?2 ")
    TeacherResources FindResourcesByTeacherEmail(String code, String email);



}
