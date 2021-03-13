package com.thesis.ELearning.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "room_shift_classes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoomShiftClass {

    @Id
    @Column(name = "room_classes_id")
    private  String id;

    @OneToOne
    @JoinColumn(name = "room_classes_teacher_fk")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "room_classes_shift")
    private RoomShift roomShift;

    @ManyToOne
    @JoinColumn(name = "room_classes_subject")
    private Subject subject;


    @Column(name = "room_classes_start_time")
    private String startTime;

    @Column(name = "room_classes_end_time")
    private String endTime;

    @Column(name = "room_classes_day")
    private String day;

    @ManyToMany
    @JoinTable(name = "room_shift_classes_students",
    joinColumns = @JoinColumn(name = "room_class_id"),
    inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> students;




}
