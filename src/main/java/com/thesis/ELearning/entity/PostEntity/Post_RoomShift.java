package com.thesis.ELearning.entity.PostEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post_RoomShift {
    private int id;
    private String roomName;
    private String shiftName;
    private String grade;
    private String section;
    private String timeStart;
    private String timeEnd;
}
