package com.thesis.ELearning.entity;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public  class User implements Comparable<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "user_email")
    @GraphQLQuery(name = "email", description = "User Email")
    private String email;

    @Column(name = "user_first_name")
    @GraphQLQuery(name = "firstName", description = "User First Name")
    private String firstName;

    @Column(name = "user_middle_name")
    private String middleName;

    @Column(name = "picture",columnDefinition = "TEXT")
    private String picture;

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


    @Column(name = "user_birthdate")
    @GraphQLQuery(name = "birthdate", description = "User BirthDate")
    private Date birthdate;

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

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updated_at;


    @Override
    public int compareTo(User o) {

        return o.getLastName().compareToIgnoreCase(lastName);
    }

    public User(String email, String firstName, String middleName, String picture, String lastName, String suffix, String gender, String password, Date birthdate, String userRole, boolean isAccountNotExpired, boolean isAccountNotLocked, boolean isCredentialNotExpired, boolean isEnabled, Date createdAt, Date updated_at) {
        this.email = email;
        this.firstName = firstName;
        this.middleName = middleName;
        this.picture = picture;
        this.lastName = lastName;
        this.suffix = suffix;
        this.gender = gender;
        this.password = password;
        this.birthdate = birthdate;
        this.userRole = userRole;
        this.isAccountNotExpired = isAccountNotExpired;
        this.isAccountNotLocked = isAccountNotLocked;
        this.isCredentialNotExpired = isCredentialNotExpired;
        this.isEnabled = isEnabled;
        this.createdAt = createdAt;
        this.updated_at = updated_at;
    }
}
