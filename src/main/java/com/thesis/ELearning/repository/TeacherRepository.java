package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.TeacherResources;
import com.thesis.ELearning.entity.RoomShiftClass;
import com.thesis.ELearning.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, String> {

    @Query(value = "SELECT t from Teacher t where t.user.userRole = 'TEACHER' and ( t.user.firstName like  %?1% or t.user.lastName like %?1% "+
            "or t.user.email like %?1% or t.id like %?1%) ORDER BY t.user.createdAt DESC ")
    Page<Teacher> Teachers(String search, Pageable pageable);

    @Query(value = "SELECT t from Teacher t where t.user.userRole = 'TEACHER' and t.status =?2 and ( t.user.firstName like  %?1% or t.user.lastName like %?1% "+
            "or t.user.email like %?1% or t.id like %?1%) ORDER BY t.user.createdAt DESC ")
    Page<Teacher> Teachers(String search, int status ,Pageable pageable);

    @Query(value = "SELECT t from Teacher t where t.id =?1")
    Teacher teacher(String id);


    @Query(value = "SELECT t from Teacher t where t.user.email = ?1")
    Teacher getTeacherByUserEmail(String email);

    @Query(value = "SELECT t from RoomShiftClass t where t.teacher.id = ?1 and t.status =?2")
    List<RoomShiftClass> getTeacherClass(String id, int status);

    @Query(value = "SELECT t from TeacherResources t where t.teacher.user.email = ?2 and (" +
            "t.code like %?1% " +
            "or t.name like %?1% " +
            "or t.description like %?1% " +
            "or t.type like %?1% ) order by t.createdAt DESC ")
    Page<TeacherResources> getTeacherResources(String search, String email, Pageable pageable);

}
