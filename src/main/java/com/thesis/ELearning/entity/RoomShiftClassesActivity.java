package com.thesis.ELearning.entity;

import lombok.*;

import javax.persistence.*;

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
    @Column(name = "room_classes_activity_id")
    private int id;

    @OneToOne
    @JoinColumn(name = "room_classes_id_fk")
    private RoomShiftClass classes;

    @OneToOne
    @JoinColumn(name = "activity_fk")
    private Activity activity;
}
