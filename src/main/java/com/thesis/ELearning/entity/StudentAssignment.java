package com.thesis.ELearning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author Kurt Orioque
 */


@Table(name = "student_assignment")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public @Data
class StudentAssignment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "grade")
    private double grade;

    @Column(name = "status")
    private int status;

    @ManyToOne()
    @JoinColumn(name = "student")
    private Student student;

    @ManyToOne()
    @JoinColumn(name = "assignment")
    private TeacherAssignment teacherAssignment;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at",updatable = false)
    private Date createdAt;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "submitted_at")
    private Date submittedAt;

    public StudentAssignment(double grade, int status, Student student, TeacherAssignment teacherAssignment, Date createdAt) {
        this.grade = grade;
        this.status = status;
        this.student = student;
        this.teacherAssignment = teacherAssignment;
        this.createdAt = createdAt;
    }
}
