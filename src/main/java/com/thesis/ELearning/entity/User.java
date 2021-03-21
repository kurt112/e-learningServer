package com.thesis.ELearning.entity;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.*;
import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
public  class User implements Comparable<User> {

    @Id
    @Column(name = "user_email")
    @GraphQLQuery(name = "email", description = "User Email")
    private String email;

    @Column(name = "user_first_name")
    @GraphQLQuery(name = "firstName", description = "User First Name")
    private String firstName;

    @Column(name = "user_middle_name")
    private String middleName;



    @Column(name= "user_last_name")
    @GraphQLQuery(name = "lastName", description = "User Last Name")
    private String lastName;

    @Column(name = "user_sufix")
    private String suffix;

    @Column(name = "user_gender")
    private String gender;


    @Column(name="user_password")
    @GraphQLQuery(name = "password", description = "UserPassword")
    private String password;

    @Column(name = "user_register_date")
    @GraphQLQuery(name = "registerDate", description = "User RegisterDate")
    private String registerDate;

    @Column(name = "user_birthdate")
    @GraphQLQuery(name = "birthdate", description = "User BirthDate")
    private String birthdate;

    @Column(name = "user_role")
    @GraphQLQuery(name = "role", description = "role")
    private String userRole;

    @Column(name = "is_account_not_expired")
    private boolean isAccountNotExpired;

    @Column(name = "is_account_not_locked")
    private boolean isAccountNotLocked;

    @Column(name = "is_credential_not_expired")
    private boolean isCredentialNotExpired;

    @Column(name ="is_enable")
    private boolean isEnabled;



    @Override
    public int compareTo(User o) {

        return o.getLastName().compareToIgnoreCase(lastName);
    }

}
