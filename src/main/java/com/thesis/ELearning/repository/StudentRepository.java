package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {

    @Query(value = "SELECT t from Student t where t.user.firstName like  %?1% or t.user.lastName like %?1% " +
            "or t.user.email like %?1%  or t.id like %?1% ORDER BY t.user.createdAt DESC")
    Page<Student> Students(String search, Pageable pageable);

    @Query(value = "SELECT t from Student t where t.status = ?2 and (t.user.firstName like  %?1% or t.user.lastName like %?1% " +
            "or t.user.email like %?1%  or t.id like %?1%) ORDER BY t.user.createdAt DESC")
    Page<Student> Students(String search,int status, Pageable pageable);

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

    // page 0 di na need
    // -1 assign but not summited
    // 1 submitted

    // assignemnt
    @Query(value = "SELECT t from StudentAssignment t where t.student.user.email =?1 and (t.status =-1 or t.status =1)")
    List<StudentAssignment> getStudentAssignment(String email);

    @Query(value = "SELECT t from StudentAssignment t where t.student.user.email =?1 and t.status =0")
    Page<StudentAssignment> getStudentAssignmentArchive(String email, Pageable pageable);

    // Quiz
    @Query(value = "SELECT t from StudentQuiz t where t.student.user.email =?1 and (t.status =-1 or t.status =1)")
    List<StudentQuiz> getStudentQuiz(String email);

    @Query(value = "SELECT t from StudentQuiz t where t.student.user.email =?1 and t.status =0")
    Page<StudentQuiz> getStudentQuizArchive(String email, Pageable pageable);

    // exam
    @Query(value = "SELECT t from StudentExam t where t.student.user.email =?1 and (t.status =-1 or t.status =1)")
    List<StudentExam> getStudentExam(String email);

    @Query(value = "SELECT t from StudentExam t where t.student.user.email =?1 and t.status =0")
    Page<StudentExam> getStudentExamArchive(String email, Pageable pageable);




}
