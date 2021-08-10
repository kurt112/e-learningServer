package com.thesis.ELearning.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "room_shift_classes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoomShiftClass {

    @Id
    @Column(name = "room_classes_id")
    private  String id;

    @ManyToOne
    @JoinColumn(name = "room_classes_teacher_fk")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "room_classes_shift")
    private RoomShift roomShift;

    @ManyToOne
    @JoinColumn(name = "room_classes_subject")
    private Subject subject;


    @Column(name = "room_classes_start_time")
    private String startTime;

    @Column(name = "room_classes_end_time")
    private String endTime;

    @Column(name = "room_classes_day")
    private String day;

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

    // Activities

    @ManyToMany
    @JoinTable(name = "room_shift_classes_students",
    joinColumns = @JoinColumn(name = "room_class_id"),
    inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;


    // for teachers file

    @OneToMany(mappedBy = "roomShiftClass")
    private List<TeacherQuizzes> teacherQuizzes;

    @OneToMany(mappedBy = "class_")
    private List<TeacherLectures> teacherLectures;

    @OneToMany(mappedBy = "roomShiftClass")
    private List<TeacherAssignment> teacherAssignments;

    @OneToMany(mappedBy = "roomShiftClass")
    private List<TeacherExams> teacherExams;

//    @OneToMany(mappedBy = "teacher_class")
//    private List<TeacherClassAttendance> teacherClassAttendances;

    @OneToMany(mappedBy = "student_class")
    private List<StudentClassAttendance> studentClassAttendances;


    public RoomShiftClass(String id, RoomShift roomShift, Subject subject,
                          Date createdAt, Date updated_at, int status) {
        this.id = id;
        this.roomShift = roomShift;
        this.subject = subject;
        this.createdAt = createdAt;
        this.updated_at = updated_at;
        this.status = status;
    }

}
