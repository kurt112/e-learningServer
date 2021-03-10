package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.RoomShiftClassesActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomShiftClassesActivityRepository extends JpaRepository<RoomShiftClassesActivity, Integer> {
    @Query(value = "SELECT t from RoomShiftClassesActivity t where  t.activity.activityTitle like %?1% " +
            "or t.activity.date_end like %?1% " +
            "or t.activity.date_created like %?1% " +
            "or t.activity.status like %?1% " +
            "or t.classes.roomShift.grade like %?1% " +
            "or t.classes.roomShift.section like %?1% " +
            "or t.classes.subject.subjectName like %?1% ")
    Page<RoomShiftClassesActivity> RoomClassesActivity(String search, Pageable pageable);

}
