package com.thesis.ELearning.entity;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "room_shift_classes_students")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomShiftClassStudents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @GraphQLQuery(name = "id")
    private int id;


    @ManyToMany
    @JoinColumn(name = "student_id")
    @GraphQLQuery(name = "students")
    private List<Student> students;

    @OneToOne
    @JoinColumn(name = "room_class_id")
    @GraphQLQuery(name = "class")
    private RoomShiftClass roomShiftClass;

}
