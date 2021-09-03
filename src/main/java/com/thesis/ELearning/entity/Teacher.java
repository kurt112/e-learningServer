package com.thesis.ELearning.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teacher")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Teacher {

    @Id
    @Column(name = "teacher_id")
    private String id;

    @Column(name = "status")
    private int status;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_user")
    private User user;

    // Relation Ship Mapping

    @OneToMany(mappedBy = "teacher")
    private Set<TeacherResources> resources;

    @OneToMany(mappedBy = "teacher")
    public Set<RoomShiftClass> roomShiftClasses;

    @OneToMany(mappedBy = "teacher")
    public Set<TeacherClassAttendance> classAttendances;

    public Teacher(String id, User user) {
        this.id = id;
        this.user = user;
    }
}
