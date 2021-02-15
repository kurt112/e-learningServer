package com.thesis.ELearning.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "teacher_activity")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeacherActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_activity_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "teacher_activity_teacher_fk")
    private Teacher teacherID;

    @ManyToOne
    @JoinColumn(name = "teacher_activity_roomactivity_fk")
    private Activity activity;
}
