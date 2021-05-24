package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.RoomShiftClassAssignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomShiftClassesAssignmentRepository extends JpaRepository<RoomShiftClassAssignment, Integer> {

    @Query(value = "SELECT t FROM RoomShiftClassAssignment t where t.resource.name like %?1% or " +
            "t.resource.description like %?1% ")
    Page<RoomShiftClassAssignment> data (String search, Pageable pageable);


    @Query(value = "SELECT t FROM RoomShiftClassAssignment t where t.roomShiftClass.teacher.user.email = ?2 and t.resource.name like %?1% or " +
            "t.resource.description like %?1% ")
    Page<RoomShiftClassAssignment> getRoomShiftClassAssignmentByTeacher(String search,String email ,Pageable pageable);

}
