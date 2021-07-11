package com.thesis.ELearning.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "room")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Room {

    @Id
    @Column(name = "room_id")
    private String id;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "room_time_start")
    private String timeStart;

    @Column(name = "room_time_end")
    private String timeEnd;

    @Column(name = "status")
    private int status;

    // relation db
    @OneToMany(mappedBy = "room")
    private List<RoomShift> roomShifts;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updated_at;



}
