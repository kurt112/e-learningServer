package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.Curriculum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface CurriculumRepository extends JpaRepository<Curriculum, String> {

    @Query(value = "SELECT t from Curriculum t where t.code like %?1% or " +
            "t.name like %?1% or " +
            "t.description like %?1% order by t.createdAt desc ")
    Page<Curriculum> curriculums(String search, Pageable pageable);

    @Query(value = "SELECT t from Curriculum t where t.status = ?2 and (t.code like %?1% or " +
            "t.name like %?1% or " +
            "t.description like %?1%) order by t.createdAt desc ")
    Page<Curriculum> curriculums(String search, int status, Pageable pageable);
}
