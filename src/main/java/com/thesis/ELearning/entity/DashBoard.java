package com.thesis.ELearning.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @Author Kurt Orioque
 */


@Table(name = "dashboard")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DashBoard {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "teacher_count")
    private int teacher_count;

    @Column(name = "subject_count")
    private int subject_count;

    @Column(name = "student_count")
    private int student_count;

    @Column(name = "room_count")
    private int room_count;

    @Column(name = "classes_count")
    private int classes_count;

    public DashBoard IncTeacherCount (){
        teacher_count++;
        return this;
    }

    public DashBoard DecTeacherCount(){
        teacher_count--;
        return this;
    }

    public DashBoard IncSubjectCount (){
        subject_count++;
        return this;
    }

    public DashBoard DecSubjectCount(){
        subject_count--;
        return this;
    }

    public DashBoard IncStudentCount (){
        student_count++;
        return this;
    }

    public DashBoard DecStudentCount(){
        student_count--;
        return this;
    }

    public DashBoard IncRoomCount (){
        System.out.println("Before romo count " + room_count);
        room_count++;
        System.out.println("The room Count " +room_count);
        return this;
    }

    public DashBoard DecRoomCount(){
        room_count--;
        return this;
    }

    public DashBoard IncClassCount (){
        classes_count++;
        return this;
    }

    public DashBoard DecClassCount(){
        classes_count--;
        return this;
    }




}
