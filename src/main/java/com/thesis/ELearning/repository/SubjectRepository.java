package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface SubjectRepository extends JpaRepository<Subject, String> {

    @Query(value = "SELECT t from Subject t where t.subjectCode like  %?1% " +
            "or t.subjectMajor like %?1%  or t.subjectMajor like %?1%  or t.status like %?1% ORDER BY t.subjectName")
    Page<Subject> subjects(String search, Pageable pageable);
}
