package com.thesis.ELearning.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "room_shift_assignment")
@Getter
@Setter
public class RoomShiftClassAssignment {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "high_grade")
    private double highGrade;

    @Column(name = "low_grade")
    private double lowGrade;

    @Column(name = "quarter")
    private int quarter;

    @Column(name = "sem")
    private int sem;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date deadline;

    @ManyToOne()
    @JoinColumn(name = "resource")
    private Resources resource;

    @ManyToOne()
    @JoinColumn(name = "room_shift_class")
    private RoomShiftClass roomShiftClass;


}
