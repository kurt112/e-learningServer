package com.thesis.ELearning.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "teacher_quizzes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherQuizzes {
    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "high_grade")
    private double highGrade;

    @Column(name = "low_grade")
    private double lowGrade;

    @Column(name = "quarter")
    private int quarter;

    @Column(name = "sem")
    private int sem;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at",updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deadline")
    private Date deadLine;

    @ManyToOne()
    @JoinColumn(name = "resource")
    private TeacherResources resource;

    @ManyToOne()
    @JoinColumn(name = "room_shift_class")
    private RoomShiftClass roomShiftClass;

    @OneToMany(mappedBy = "quiz")
    private Set<StudentQuiz> studentQuizs;

    public TeacherQuizzes(String code, double highGrade, double lowGrade, int quarter, int sem, String description, Date createdAt, Date deadLine, TeacherResources resource, RoomShiftClass roomShiftClass) {
        this.code = code;
        this.highGrade = highGrade;
        this.lowGrade = lowGrade;
        this.quarter = quarter;
        this.sem = sem;
        this.description = description;
        this.createdAt = createdAt;
        this.deadLine = deadLine;
        this.resource = resource;
        this.roomShiftClass = roomShiftClass;
    }
}
