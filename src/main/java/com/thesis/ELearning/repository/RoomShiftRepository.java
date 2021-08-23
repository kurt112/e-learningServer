package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.RoomShift;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomShiftRepository extends JpaRepository<RoomShift, String> {
    @Query(value = "SELECT t from RoomShift t where t.timeEnd like  %?1% or t.roomShiftName like %?1% or " +
            "t.timeStart like %?1% or t.grade like %?1% or t.section like %?1% or t.room.roomName like %?1% ORDER BY t.createdAt DESC")
    Page<RoomShift> RoomShift(String search, Pageable pageable);

    @Query(value = "SELECT t from RoomShift t where t.status =?2 and (t.timeEnd like  %?1% or t.roomShiftName like %?1% or " +
            "t.timeStart like %?1% or t.grade like %?1% or t.section like %?1% or t.room.roomName like %?1%) ORDER BY t.createdAt DESC")
    Page<RoomShift> RoomShift(String search, int status, Pageable pageable);
}
