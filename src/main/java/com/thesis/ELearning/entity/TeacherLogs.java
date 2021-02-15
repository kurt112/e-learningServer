package com.thesis.ELearning.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "teacher_logs")
@NoArgsConstructor
@AllArgsConstructor
public class TeacherLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_logs_id")
    private int id;

//    @ManyToOne
//    @JoinColumn(name = "teacher_logs_teacher_id_fk")
//    @JsonIgnore
//    private Teacher teacherID;

//    @ManyToOne
//    @JoinColumn(name = "teacher_logs_rooms_fk")
//    @JsonIgnore
//    private Room room;

    @Column(name = "teacher_date")
    private String date;

}
