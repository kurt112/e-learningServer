package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.RoomShift;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomShiftRepository extends JpaRepository<RoomShift, Integer> {
    @Query(value = "SELECT t from RoomShift t where t.timeEnd like  %?1% or t.roomShiftName like %?1% or " +
            "t.timeStart like %?1% or t.grade like %?1% or t.section like %?1% or t.room.roomName like %?1% order by  t.room.roomName")
    Page<RoomShift> RoomShift(String search, Pageable pageable);
}
