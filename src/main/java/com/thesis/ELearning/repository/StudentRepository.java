package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, String> {

    @Query(value = "SELECT t from Student t where t.user.firstName like  %?1% or t.user.lastName like %?1% " +
            "or t.user.email like %?1% or t.user.birthdate like %?1% or t.user.registerDate like %?1%  or t.id like %?1% ORDER BY t.user.lastName DESC")
    Page<Student> Students(String search, Pageable pageable);



    @Query(value = "SELECT t from Student t where lower(concat(t.user.lastName,t.user.firstName, t.id)) like %?1%")
    Page<Student>StudentRoomShiftTransfer(String search, Pageable pageable);

}
