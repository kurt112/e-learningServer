package com.thesis.ELearning.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "room_shift")
@Getter
@Setter
public
class RoomShift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_shift")
    private int id;

    @Column(name = "grade")
    private String grade;
    @Column(name = "section")
    private String section;
    @Column(name = "time_start")
    private String timeStart;
    @Column(name = "time_end")
    private String timeEnd;
    @Column(name = "room_shift_name")
    private String roomShiftName;

    // relation entity
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToMany(mappedBy ="roomShift", cascade = CascadeType.ALL)
    private List<RoomShiftClass> roomShiftClasses;
}
