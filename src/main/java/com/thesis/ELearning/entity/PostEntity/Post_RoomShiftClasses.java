package com.thesis.ELearning.entity.PostEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Post_RoomShiftClasses {
    private String roomId;
    private String subjectId;
    private String classId;
    private String roomName;
    private String grade;
    private String section;
    private String subjectName;
    private String teacherName;
    private String day;
    private String startTime;
    private String endTime;
}
