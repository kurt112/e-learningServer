package com.thesis.ELearning.entity;


import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "room_shift")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public
class RoomShift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_shift")
    @GraphQLQuery(name = "id")
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

    @Column(name = "status")
    private int status;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updated_at;


    // relation entity

    @OneToOne
    @JoinColumn(name = "adviser")
    private Teacher teacher;


    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToMany(mappedBy ="roomShift", cascade = CascadeType.ALL)
    private List<RoomShiftClass> roomShiftClasses;

    @ManyToMany()
    @JoinTable(name = "room_shift_students",
            joinColumns = @JoinColumn(name = "room_shift_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;

    public RoomShift(int id, String grade, String section, String timeStart, String timeEnd, String roomShiftName, Room room) {
        this.id = id;
        this.grade = grade;
        this.section = section;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.roomShiftName = roomShiftName;
        this.room = room;
    }
}
