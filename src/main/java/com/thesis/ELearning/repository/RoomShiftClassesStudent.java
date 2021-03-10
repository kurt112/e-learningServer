package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.RoomShiftClassStudents;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomShiftClassesStudent extends JpaRepository <RoomShiftClassStudents,Integer> {

//    @Query(value = "SELECT t from RoomShiftClassStudents t where  t.roomShiftClass.id = ?2 and " +
//            "lower(concat( t.students.size, t.student.user.firstName, t.student.id)) like %?1%"
//    )
//    Page<RoomShiftClassStudents> studentInRoomClass(String search, String roomClassId, Pageable pageable);

//    @Query(value = "SELECT t from RoomShiftClassStudents t where not t.roomShiftClass.id = ?2 and " +
//            "lower(concat( t.student.user.lastName, t.student.user.firstName, t.student.id)) like %?1%"
//    )
//    Page<RoomShiftClassStudents> studentNotInRoomClass(String search, String roomClassId, Pageable pageable);

}
