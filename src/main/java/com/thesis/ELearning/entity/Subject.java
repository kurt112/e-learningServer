package com.thesis.ELearning.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subject")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Subject {
    @Id
    @Column(name = "subject_code")
    private String subjectCode;

    @Column(name="subject_name")
    private String subjectName;

    @Column(name="subject_major")
    private String subjectMajor;

    @Column(name = "subject_status")
    private String status;

    @OneToMany(mappedBy = "subject")
    @JsonIgnore
    private List<RoomShiftClass> roomShiftClasses;

}
