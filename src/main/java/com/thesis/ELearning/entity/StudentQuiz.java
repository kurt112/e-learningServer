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
@Table(name = "student_quiz")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public @Data
class StudentQuiz {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "grade")
    private double grade;

    @Column(name = "status")
    private int status;

    @Column(name = "location")
    private String location;

    @Column(name = "response")
    private String response;

    @ManyToOne()
    @JoinColumn(name = "student")
    private Student student;

    @ManyToOne()
    @JoinColumn(name = "quiz")
    private TeacherQuizzes quiz;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at",updatable = false)
    private Date createdAt;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "submitted_at")
    private Date submittedAt;

    public StudentQuiz(double grade, int status, Student student, TeacherQuizzes quiz, Date createdAt) {
        this.grade = grade;
        this.status = status;
        this.student = student;
        this.quiz = quiz;
        this.createdAt = createdAt;
    }

}
