package com.thesis.ELearning.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "room_classes_id_fk")
    private RoomShiftClass classes;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "activity_fk")
    private Activity activity;
}
