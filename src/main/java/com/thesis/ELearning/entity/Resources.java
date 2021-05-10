package com.thesis.ELearning.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "resources")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Resources {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updated_at;

    @Column(name = "status")
    private String status;

    @ManyToMany()
    @JoinTable(
            name = "teacher_resources",
            joinColumns = @JoinColumn(name = "resources"),
            inverseJoinColumns = @JoinColumn(name = "teacher"))
    private List<Teacher> teachers;

    @ManyToMany()
    @JoinTable(
            name = "student_resources",
            joinColumns = @JoinColumn(name = "resources"),
            inverseJoinColumns = @JoinColumn(name = "student"))
    private List<Student> students;



    public void addTeacher(Teacher teacher) {
        if(teachers == null) teachers = new ArrayList<>();

        teachers.add(teacher);

    }

    public String getCreatedAt() {
        return createdAt.toString();
    }
}
