package com.thesis.ELearning.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "room")
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



}
