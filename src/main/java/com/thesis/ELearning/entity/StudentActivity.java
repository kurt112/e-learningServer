package com.thesis.ELearning.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "student_activity")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_activity_id")
    private int id;


    @Column(name = "grade")
    private double grade;

    @Column(name = "status")
    private String status;


    @ManyToOne()
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne()
    @JoinColumn(name = "room_class_id")
    private RoomShiftClass roomShiftClass;

    @ManyToOne()
    @JoinColumn(name = "activity_id")
    private Activity activity;




}
