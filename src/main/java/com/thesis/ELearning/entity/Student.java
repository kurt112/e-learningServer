package com.thesis.ELearning.entity;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public  class Student {
    @Id
    @Column(name = "student_id")
    @GraphQLQuery(name = "student_id", description = "student id")
    private  String id;

    @OneToOne
    @JoinColumn(name = "student_user")
    @GraphQLQuery(name = "user", description = "Student User")
    private User user;

    @ManyToMany(mappedBy = "students")
    @GraphQLQuery(name = "classroom")
    private List<RoomShiftClassStudents> roomShiftClassStudents;

    public Student(String id, User user) {
        this.id = id;
        this.user = user;
        roomShiftClassStudents = new ArrayList<>();
    }
}
