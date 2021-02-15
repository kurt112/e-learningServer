package com.thesis.ELearning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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



}
