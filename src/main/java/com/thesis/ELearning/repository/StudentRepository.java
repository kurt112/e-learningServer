package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.RoomShift;
import com.thesis.ELearning.entity.RoomShiftClass;
import com.thesis.ELearning.entity.Student;
import com.thesis.ELearning.entity.StudentAssignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {

    @Query(value = "SELECT t from Student t where t.user.firstName like  %?1% or t.user.lastName like %?1% " +
            "or t.user.email like %?1%  or t.id like %?1% ORDER BY t.user.createdAt DESC")
    Page<Student> Students(String search, Pageable pageable);

    @Query(value = "SELECT t from Student t where " +
             "lower(concat(t.user.lastName, t.user.firstName, t.id)) like %?1% order by t.user.lastName")
    Page<Student> getStudentForRoomShift(String search, Pageable pageable);

    @Query(value = "SELECT t.roomShifts from Student t where t.id = ?1")
    List<RoomShift> getRoomShiftOfStudent(String id);

    @Query(value = "SELECT t from Student  t where t.user.email = ?1 ")
    Student getStudentByUserEmail(String id);

    @Query(value = "SELECT distinct t from RoomShiftClass t join t.students student " +
            "where student.user.email=?1 and t.status =?2")
    List<RoomShiftClass> roomShiftClass(String email, int status);



    // student to do list


    // page -1 di na need
    @Query(value = "SELECT t from StudentAssignment t where t.student.user.email =?1 and t.status > -1")
    List<StudentAssignment> getStudentAssignment(String email);

    @Query(value = "SELECT t from StudentAssignment t where t.student.user.email =?1 and t.status =-1")
    Page<StudentAssignment> getStudentAssignmentArchive(String email, Pageable pageable);

}
