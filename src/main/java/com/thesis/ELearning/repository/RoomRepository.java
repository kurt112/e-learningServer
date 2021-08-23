package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public  interface RoomRepository extends JpaRepository<Room, String> {
    @Query(value = "SELECT t from Room t where t.roomName like  %?1% or " +
            "t.timeStart like %?1% or t.timeEnd like %?1% ORDER BY t.createdAt DESC")
    Page<Room> Rooms(String search, Pageable pageable);

    @Query(value = "SELECT t from Room t where t.status = ?2 and (t.roomName like  %?1% or t.timeStart like %?1% or t.timeEnd like %?1%) ORDER BY t.createdAt DESC")
    Page<Room> Rooms(String search, int status ,Pageable pageable);

}
