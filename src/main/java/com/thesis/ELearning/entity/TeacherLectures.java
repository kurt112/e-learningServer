package com.thesis.ELearning.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "teacher_lecture")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeacherLectures {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "quarter")
    private int quarter;

    @Column(name = "sem")
    private int sem;

    @ManyToOne()
    @JoinColumn(name="resource")
    private TeacherResources resource;

    @ManyToOne()
    @JoinColumn(name = "class")
    private RoomShiftClass class_;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updated_at;
}
