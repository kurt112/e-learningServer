package com.thesis.ELearning.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Set;
import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "curriculum")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Curriculum {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private int status;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updated_at;

    @ManyToMany
    @JoinTable(name = "curriculum_subject",
            joinColumns = @JoinColumn(name = "curriculum_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subjects;


    @OneToMany(mappedBy = "curriculum")
    private Set<RoomShift> roomShifts;
}
