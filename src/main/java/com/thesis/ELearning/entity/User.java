package com.thesis.ELearning.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.*;
import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "user")
@Getter
@Setter
public  class User implements Comparable<User> {

    @Id
    @Column(name = "user_email")
    @GraphQLQuery(name = "user_email", description = "User Email")
    private String email;

    @Column(name = "user_first_name")
    @GraphQLQuery(name = "userFirstName", description = "User First Name")
    private String firstName;

    @Column(name= "user_last_name")
    @GraphQLQuery(name = "userLastName", description = "User Last Name")
    private String lastName;

    @Column(name="user_password")
    @GraphQLQuery(name = "userPassword", description = "UserPassword")
    private String password;

    @Column(name = "user_register_date")
    @GraphQLQuery(name = "userRegisterDate", description = "User RegisterDate")
    private String registerDate;

    @Column(name = "user_birthdate")
    @GraphQLQuery(name = "userBirthDate", description = "User BirthDate")
    private String birthdate;

//    @JsonIgnore
//    @OneToOne(mappedBy = "user")
//    private Student student;
//
//    @JsonIgnore
//    @OneToOne(mappedBy = "user")
//    private Teacher teacherID;

    public User(String email, String firstName, String lastName, String password, String registerDate, String birthdate) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.registerDate = registerDate;
        this.birthdate = birthdate;
    }

    @Override
    public int compareTo(User o) {

        return o.getLastName().compareToIgnoreCase(lastName);
    }


}
