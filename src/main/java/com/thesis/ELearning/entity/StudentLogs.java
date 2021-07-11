package com.thesis.ELearning.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "student_logs")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_logs_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_logs_student_fk")
    private Student student;

    @Column(name = "student_logs_date")
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
