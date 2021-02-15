package com.thesis.ELearning.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "teacher_attendance")
@AllArgsConstructor
@NoArgsConstructor
public class TeacherAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_attendance_id")
    private int id;

//    @ManyToOne
//    @JoinColumn(name = "teacher_attendance_teacher_id_fk")
//    private Teacher teacherID;

    @Column(name = "teacher_date")
    private String date;
}
