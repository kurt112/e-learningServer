package com.thesis.ELearning.utils;

import com.thesis.ELearning.entity.TeacherResources;
import com.thesis.ELearning.entity.RoomShift;
import com.thesis.ELearning.entity.Subject;
import com.thesis.ELearning.entity.Teacher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class StorageService {
    private final String userDirectory = System.getProperty("user.dir");
    public String UploadActivityClass(MultipartFile file, Subject subject, RoomShift roomShift, String ActivityName, String ActivityType) throws IOException {
        String shiftName=  roomShift.getGrade() + " - " + roomShift.getSection();
        String subjectName = subject.getSubjectName() + " - " + subject.getSubjectCode();
        String date = FormattedDate.getDateNow().replaceAll("/","-");
        File RoomPath = new File(userDirectory+"\\Room");
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

    public String UploadResource(MultipartFile file, TeacherResources teacherResources, Teacher teacher) throws IOException{
        File TeacherPath = new File(userDirectory+"/upload/Teacher");
        File TeacherName = new File(TeacherPath+"/"+teacher.getUser().getEmail());
        File Resource = new File(TeacherName + "/Resource");
        File ResourceType = new File(Resource+"/"+ teacherResources.getType());
        File ResourceName = new File(ResourceType+ "/"+ teacherResources.getName());

        System.out.println(TeacherPath.exists());
        if(!TeacherName.exists()) TeacherName.mkdir();
        if(!Resource.exists()) Resource.mkdir();
        if(!ResourceType.exists()) ResourceType.mkdir();
        if(!ResourceName.exists()) ResourceName.mkdir();

        file.transferTo(new File(ResourceName.getPath()+"/"+file.getOriginalFilename()));
        return  ResourceName.getPath()+"/"+file.getOriginalFilename();
    }
}
