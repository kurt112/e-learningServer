package com.thesis.ELearning.entity;


import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_activity")
@Getter
@Setter
public
class StudentActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_activity_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_activity_student_fk")
    private Student student;

//    @ManyToOne
//    @JoinColumn(name = "student_activity_activty_fk")
//    private Activity activity;

    @Column(name = "student_activity_status")
    private String status;

}
