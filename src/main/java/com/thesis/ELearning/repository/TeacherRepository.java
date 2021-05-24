package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.Resources;
import com.thesis.ELearning.entity.RoomShiftClass;
import com.thesis.ELearning.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, String> {

    @Query(value = "SELECT t from Teacher t where t.user.firstName like  %?1% or t.user.lastName like %?1% " +
            "or t.user.email like %?1% or t.user.birthdate like %?1%  or t.id like %?1% ORDER BY t.user.lastName")
    Page<Teacher> Teachers(String search, Pageable pageable);

    @Query(value = "SELECT t from Teacher t where t.id =?1")
    Teacher teacher(String id);


    @Query(value = "SELECT t from Teacher t where t.user.email = ?1")
    Teacher getTeacherByUserEmail(String email);

    @Query(value = "SELECT t from RoomShiftClass t where t.teacher.id = ?1 and t.status =?2")
    List<RoomShiftClass> getTeacherClass(String id, int status);

    @Query(value = "SELECT t from Resources t where t.teacher.user.email = ?2 and (" +
            "t.code like %?1% " +
            "or t.name like %?1% " +
            "or t.description like %?1% " +
            "or t.type like %?1% )")
    Page<Resources> getTeacherResources(String search, String email, Pageable pageable);
}
