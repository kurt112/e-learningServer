package com.thesis.ELearning.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "subject")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "subject_code")
    private String subjectCode;

    @Column(name="subject_name")
    private String subjectName;

    @Column(name="subject_major")
    private String subjectMajor;

    @Column(name = "subject_status")
    private int status;

    @OneToMany(mappedBy = "subject")
    @JsonIgnore
    private Set<RoomShiftClass> roomShiftClasses;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at",updatable = false)
    private Date updated_at;

    @ManyToMany(mappedBy = "subjects")
        private Set<Curriculum> curriculumList;


}
