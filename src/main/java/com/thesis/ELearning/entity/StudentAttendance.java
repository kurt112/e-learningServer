package com.thesis.ELearning.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

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

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at",updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updated_at;

}
