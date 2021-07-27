package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


@Transactional
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    @Query(value = "SELECT t from Subject t where t.subjectCode like  %?1% " +
            "or t.subjectMajor like %?1%  or t.subjectMajor like %?1%  or t.status like %?1% ORDER BY t.createdAt DESC")
    Page<Subject> subjects(String search, Pageable pageable);

    @Query(value = "SELECT t FROM Subject t where t.subjectCode =?1")
    Subject findSubjectBySubjectCode(String code);

    @Query(value = "SELECT t from Subject t where trim(lower(concat(t.subjectName , t.subjectCode))) like %?1%")
    Page<Subject> searchSubjectByNameAndCode(String search, Pageable pageable);

    @Modifying
    @Query(value = "DELETE FROM Subject t WHERE t.subjectCode = ?1")
    void deleteSubjectBySubjectCode(String subjectCode);
}
