package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, String> {

    @Query(value = "SELECT t from Teacher t where t.user.firstName like  %?1% or t.user.lastName like %?1% " +
            "or t.user.email like %?1% or t.user.birthdate like %?1% or t.user.registerDate like %?1%  or t.id like %?1% ORDER BY t.user.lastName")
    Page<Teacher> Teachers(String search, Pageable pageable);

    @Query(value = "SELECT t from Teacher t where t.id =?1")
    Teacher teacher(String id);
}
