package com.thesis.ELearning.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "teacher_resources")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeacherResources {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "type")
    private String type;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updated_at;

    @Column(name = "status")
    private int status;


    @ManyToOne()
    @JoinColumn(name = "teacher")
    private Teacher teacher;

    @ManyToMany()
    @JoinTable(
            name = "student_resources",
            joinColumns = @JoinColumn(name = "resources"),
            inverseJoinColumns = @JoinColumn(name = "student"))
    private List<Student> students;


    public TeacherResources(String code, String name, String location, String type, String description, Date createdAt) {
        this.code = code;
        this.name = name;
        this.location = location;
        this.type = type;
        this.description = description;
        this.createdAt = createdAt;
    }

//    public void addTeacher(Teacher teacher) {
//        if(teachers == null) teachers = new ArrayList<>();
//
//        teachers.add(teacher);
//
//    }

    public String getCreatedAt() {
        return createdAt.toString();
    }
}
