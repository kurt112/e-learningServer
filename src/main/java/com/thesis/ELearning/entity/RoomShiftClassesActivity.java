package com.thesis.ELearning.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "room_shift_classes_activity")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public
class RoomShiftClassesActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @OneToOne
    @JoinColumn(name = "room_classes_id_fk")
    private RoomShiftClass classes;

    @OneToOne
    @JoinColumn(name = "room_classes_activity_id")
    private Activity activity;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updated_at;



}
