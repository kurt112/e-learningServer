package com.thesis.ELearning.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "room_classes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoomClass {

    @Id
    @Column(name = "room_classes_id")
    private  String id;

    @OneToOne
    @JoinColumn(name = "room_classes_teacher_fk")
    private Teacher teacher;

    @OneToOne
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


}
