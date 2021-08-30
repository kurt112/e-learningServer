package com.thesis.ELearning.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @Author Kurt Orioque
 */


@Entity
@Table(name = "student_grade")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentGrade {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "class")
    private RoomShiftClass roomShiftClass;

    @Column(name = "grade")
    private double grade;

    @ManyToOne()
    @JoinColumn(name = "student")
    private Student student;

    public StudentGrade(RoomShiftClass roomShiftClass, double grade, Student student) {
        this.roomShiftClass = roomShiftClass;
        this.grade = grade;
        this.student = student;
    }
}