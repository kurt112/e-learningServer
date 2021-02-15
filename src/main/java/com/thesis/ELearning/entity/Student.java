package com.thesis.ELearning.entity;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.*;

import javax.persistence.*;

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



}
