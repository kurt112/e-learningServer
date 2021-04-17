package com.thesis.ELearning.entity;

import lombok.*;

import javax.persistence.*;
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



    @OneToMany(mappedBy = "teacher")
    public List<RoomShiftClass> roomShiftClassList;

    public Teacher(String id, User user, String link) {
        this.id = id;
        this.user = user;
        this.link = link;
    }
}
