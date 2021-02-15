package com.thesis.ELearning.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "student_attendance")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_attendance_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_attendance_fk_student")
    Student student;

    @Column(name = "student_attendance_date")
    private String date;
}
