package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.RoomShiftClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomShiftClassesRepository extends JpaRepository<RoomShiftClass, String> {

    @Query(value = "SELECT t from RoomShiftClass t where t.roomShift.room.roomName like %?1% " +
            "or t.roomShift.grade like %?1% " +
            "or t.roomShift.section like %?1% " +
            "or t.subject.subjectName like %?1% " +
            "or concat(t.teacher.user.lastName, t.teacher.user.firstName) like %?1% " +
            "or t.day like %?1% " +
            "or t.startTime like %?1%" +
            "or t.endTime like %?1% " +
            "order by t.roomShift.room.roomName")
    Page<RoomShiftClass> RoomClasses(String search, Pageable pageable);


    @Query(value = "SELECT t from RoomShiftClass  t where  t.roomShift.id = ?1 and t.subject.subjectCode = ?2")
    RoomShiftClass FindRoomClassByShiftAndSubject(int ShiftId, String subjectCode);


}