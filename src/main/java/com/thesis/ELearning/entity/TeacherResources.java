package com.thesis.ELearning.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "teacher_resources")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private Set<Student> students;

    @OneToMany(mappedBy = "resource")
    private Set<TeacherAssignment> teacherAssignments;

    @OneToMany(mappedBy = "resource")
    private Set<TeacherExams> teacherExams;

    @OneToMany(mappedBy = "resource")
    private Set<TeacherLectures> teacherLectures;

    @OneToMany(mappedBy = "resource")
    private Set<TeacherQuizzes>teacherQuizzes;


    public TeacherResources(String code, String name, String location, String type, String description, Date createdAt) {
        this.code = code;
        this.name = name;
        this.location = location;
        this.type = type;
        this.description = description;
        this.createdAt = createdAt;
    }



    public String getCreatedAt() {
        return createdAt.toString();
    }
}
