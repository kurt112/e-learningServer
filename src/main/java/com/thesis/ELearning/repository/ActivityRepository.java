package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.Activity;
import com.thesis.ELearning.entity.RoomClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {
    @Query(value = "SELECT t from Activity t where t.type like %?1% " +
            "or t.description like %?1% " +
            "or t.date_end like %?1% " +
            "or t.activityTitle like %?1%"
    )
    Page<Activity> RoomClasses(String search, Pageable pageable);
}
