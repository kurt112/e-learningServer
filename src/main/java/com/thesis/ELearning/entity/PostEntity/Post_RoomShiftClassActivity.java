package com.thesis.ELearning.entity.PostEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post_RoomShiftClassActivity {
    private int id;
    private String title;
    private String subjectName;
    private String grade;
    private String section;
    private String date_created;
    private String date_end;
    private String link;
    private String status;
}
