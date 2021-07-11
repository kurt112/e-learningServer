package com.thesis.ELearning.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

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

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at",updatable = false)
    private Date updated_at;

}
