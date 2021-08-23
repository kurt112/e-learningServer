package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    @Query(value = "SELECT t from User t where t.userRole = ?2 and (t.firstName like  %?1% or t.lastName like %?1% " +
            "or t.email like %?1%) ORDER BY t.createdAt DESC")
    Page<User> usersWitRole(String search,String role, Pageable pageable);

    @Query(value = "SELECT t from User t where t.isAccountNotLocked = ?3 and t.userRole = ?2 and (t.firstName like  %?1% or t.lastName like %?1% " +
            "or t.email like %?1%) ORDER BY t.createdAt DESC")
    Page<User> usersWitRole(String search,String role, boolean status, Pageable pageable);


    @Query(value = "SELECT t from User  t where t.email = ?1")
    User findUserEmail (String username);
}
