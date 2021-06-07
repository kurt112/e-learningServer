package com.thesis.ELearning.entity;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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

    public Student(String id, User user) {
        this.id = id;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("The o");
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getId().equals(student.getId());
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", user=" + user +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
