package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AutoCompleteRepository extends JpaRepository<Room, Object> {
    @Query(value = "SELECT t.roomName, t.id from Room t where lower(t.roomName)  like %?1%")
    Page<Object> Room_AutoComplete(String search, Pageable pageable);

    @Query(value = "SELECT t.user.lastName, t.user.firstName, t.user.email from Teacher t " +
            "where t.user.userRole = 'TEACHER' and (lower(concat(t.user.lastName,t.user.firstName, t.user.email)) like %?1%) order by t.user.lastName")
    Page<Object> Teacher_AutoComplete(String search, Pageable pageable);

    @Query(value = "SELECT t.subjectName, t.subjectCode, t.id from Subject t " +
    "where lower(concat(t.subjectName, t.subjectCode)) like %?1%")
    Page<Object> Subject_AutoComplete(String search, Pageable pageable);

    @Query(value = "SELECT t.grade, t.section, t.id from RoomShift t " +
            "where lower(concat(t.grade,t.section,t.id)) like %?1%")
    Page<Object> RoomShift_AutoComplete(String search, Pageable pageable);

    @Query( value= "SELECT t.name, t.code from Curriculum t " +
            "where lower(concat(t.name,t.code)) like %?1%")
    Page<Object>  Curriculum_AutoComplete(String search, Pageable pageable);

    @Query(value = "SELECT t.roomShift.grade, t.roomShift.section, t.id from RoomShiftClass t WHERE lower(concat(t.roomShift.grade, t.roomShift.section, t.id)) like  %?1%")
    Page<Object> RoomClass(String search, Pageable pageable);


    @Query(value = "SELECT t.subject.subjectName, t.subject.subjectMajor, t.subject.subjectCode FROM RoomShiftClass t WHERE t.roomShift.id = ?1")
    Page<Object> SubjectsBasedOnRoomShift(int roomShiftID, Pageable pageable);

    // teacher list
    @Query(value = "SELECT t.roomShift.grade, t.roomShift.section, t.id from RoomShiftClass t WHERE t.teacher.user.email =?2 and lower(concat(t.roomShift.grade, t.roomShift.section, t.id)) like  %?1%")
    Page<Object> getTeacherClass(String search,String email, Pageable pageable);

    @Query(value = "SELECT t.name, t.code FROM TeacherResources t where t.teacher.user.email =?2 and t.type='Assignment' and lower(concat(t.name,t.code)) like %?1%")
    Page<Object> getTeacherResourcesAssignment(String search, String email, Pageable pageable);

    @Query(value = "SELECT t.name, t.code from TeacherResources t where lower(concat(t.name, t.code)) like %?1% and (t.type ='Lecture' and t.teacher.user.email =?2)")
    Page<Object> getTeacherResourceLecture(String search, String email, Pageable pageable);

    @Query(value = "SELECT t.name, t.code FROM TeacherResources t where t.teacher.user.email =?2 and t.type='Exam' and lower(concat(t.name,t.code)) like %?1%")
    Page<Object> getTeacherExams(String search, String email, Pageable pageable);

    @Query(value = "SELECT t.name, t.code FROM TeacherResources t where t.teacher.user.email =?2 and t.type='Quiz' and lower(concat(t.name,t.code)) like %?1%")
    Page<Object> getTeacherQuiz(String search, String email, Pageable pageable);

}

