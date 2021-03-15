package com.thesis.ELearning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "activity")
@AllArgsConstructor
@NoArgsConstructor
public @Data class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private int id;


    @Column(name = "activity_title")
    private String activityTitle;

    @Column(name = "activity_link")
    private String link;

    @Column(name = "activity_date_created")
    private String date_created;

    @Column(name = "activity_date_deadline")
    private String date_end;

    @Column(name = "activity_type")
    private String type;


    @Column(name = "activity_description")
    private String description;

    @Column(name = "activity_status")
    private String status;

    @ManyToMany(mappedBy = "activities")
    private List<RoomShiftClass> roomShiftClasses;


    public Activity(int id, String activityTitle, String link, String date_created, String date_end, String type, String description, String status) {
        this.id = id;
        this.activityTitle = activityTitle;
        this.link = link;
        this.date_created = date_created;
        this.date_end = date_end;
        this.type = type;
        this.description = description;
        this.status = status;
    }
}
