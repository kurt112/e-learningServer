package com.thesis.ELearning.entity;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
public @Data class Student {
    @Id
    @Column(name = "student_id")
    @GraphQLQuery(name = "student_id", description = "student id")
    private  String id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_user")
    @GraphQLQuery(name = "user", description = "Student User")
    private User user;


    @ManyToMany(mappedBy = "students")
    @GraphQLQuery(name = "roomShifts")
    private List<RoomShift> roomShifts;

    @ManyToMany(mappedBy = "students")
    @GraphQLQuery(name = "roomShiftClasses")
    private List<RoomShiftClass> roomShiftClasses;


    @ManyToMany(mappedBy = "students")
    private List<TeacherResources> resources;

    @OneToMany(mappedBy ="student")
    private List<StudentClassAttendance> classAttendances;

    @OneToMany(mappedBy = "student")
    private List<StudentAssignment> assignments;

    public Student(String id, User user) {
        this.id = id;
        this.user = user;
    }


    public Student() {

    }
}
