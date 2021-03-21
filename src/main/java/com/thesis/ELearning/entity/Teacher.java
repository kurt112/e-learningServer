package com.thesis.ELearning.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

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



    public void Create_Teacher(){

    }

}
