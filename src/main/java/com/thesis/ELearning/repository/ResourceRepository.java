package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.Resources;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ResourceRepository extends JpaRepository<Resources, String> {
    @Query(value = "SELECT t from Resources t where ("+
            "t.code like  %?1% or t.name like %?1% or t.type like %?1% or t.status like %?1%"+")")
    Page<Resources> Resources(String search, int id,Pageable pageable);
}
