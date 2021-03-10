package com.thesis.ELearning.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "room_shift_students")
@Entity
@Getter
@Setter
public class RoomShiftStudents {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne()
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToOne
    @JoinColumn(name = "room_shift_id")
    private RoomShift roomShift;
}
