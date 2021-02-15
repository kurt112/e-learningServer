package com.thesis.ELearning.utils;

import com.thesis.ELearning.entity.RoomShift;
import com.thesis.ELearning.entity.Subject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class StorageService {
    public String UploadActivityClass(MultipartFile file, Subject subject, RoomShift roomShift, String ActivityName, String ActivityType) throws IOException {

        String shiftName=  roomShift.getGrade() + " - " + roomShift.getSection();
        String subjectName = subject.getSubjectName() + " - " + subject.getSubjectCode();
        String date = FormattedDate.getDateNow().replaceAll("/","-");
        File RoomPath = new File("C:\\E-learning\\Room");
        File RoomShiftPath = new File(RoomPath+"\\"+shiftName);
        File SubjectPath = new File(RoomShiftPath+"\\"+subjectName);

        File ActivityTypePath = new File(SubjectPath+"\\"+ActivityType);
        File ActivityNamePath = new File(ActivityTypePath+"\\"+ActivityName+ " " + date);
        if(!RoomShiftPath.exists()) RoomShiftPath.mkdir();
        if(!SubjectPath.exists()) SubjectPath.mkdir();
        if(!ActivityTypePath.exists()) ActivityTypePath.mkdir();
        if(!ActivityNamePath.exists()) ActivityNamePath.mkdir();




        file.transferTo(new File(ActivityNamePath.getPath()+"\\"+file.getOriginalFilename()));

        return ActivityNamePath.getPath()+"\\"+file.getOriginalFilename();

    }
}
