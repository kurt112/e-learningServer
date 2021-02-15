package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.Activity;
import com.thesis.ELearning.entity.RoomClassesActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomClassesActivityRepository extends JpaRepository<RoomClassesActivity, Integer> {
    @Query(value = "SELECT t from RoomClassesActivity t")
    Page<RoomClassesActivity> RoomClassesActivity(String search, Pageable pageable);

}
