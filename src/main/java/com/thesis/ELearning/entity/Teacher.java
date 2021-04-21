package com.thesis.ELearning.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_user")
    private User user;


    @Column(name = "teacher_link")
    private String link;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updated_at;


    @OneToMany(mappedBy = "teacher")
    public List<RoomShiftClass> roomShiftClasses;


    @OneToMany(mappedBy = "teacher")
    public List<TeacherActivity> activities;

    public Teacher(String id, User user, String link) {
        this.id = id;
        this.user = user;
        this.link = link;
    }
}
