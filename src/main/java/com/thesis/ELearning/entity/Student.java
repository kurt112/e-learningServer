package com.thesis.ELearning.entity;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student {
    @Id
    @Column(name = "student_id")
    @GraphQLQuery(name = "student_id", description = "student id")
    private  String id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_user")
    @GraphQLQuery(name = "user", description = "Student User")
    private User user;

    @Column(name = "status")
    private int status;

    @ManyToMany(mappedBy = "students",cascade = {CascadeType.PERSIST})
    @GraphQLQuery(name = "roomShifts")
    private Set<RoomShift> roomShifts;

    @ManyToMany(mappedBy = "students", cascade = {CascadeType.PERSIST})
    @GraphQLQuery(name = "roomShiftClasses")
    private Set<RoomShiftClass> roomShiftClasses;

    @ManyToMany(mappedBy = "students")
    private Set<TeacherResources> resources;

    @OneToMany(mappedBy ="student")
    private Set<StudentClassAttendance> classAttendances;

    @OneToMany(mappedBy = "student")
    private Set<StudentAssignment> assignments;

    @OneToMany(mappedBy = "student")
    private Set<StudentExam> exams;

    @OneToMany(mappedBy = "student")
    private Set<StudentQuiz> quizzes;

    public Student(String id, User user) {
        this.id = id;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Student student = (Student) o;

        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return 1128121276;
    }
}
