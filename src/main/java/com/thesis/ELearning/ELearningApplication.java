package com.thesis.ELearning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class ELearningApplication {

    @Autowired
    public static void main(String[] args) {

        String userDirectory = System.getProperty("user.dir");
        File AllDataContainer = new File(userDirectory);

        File upload = new File(AllDataContainer.getPath()+"/upload");
        if(!upload.exists()) upload.mkdir();
        String parentPath = upload.getPath();
        File Student_File = new File(parentPath+"/Student");
        File Teacher_File = new File(parentPath+ "/Teacher");
        File Room_File = new File(parentPath+"/Room");
//
        if(!Student_File.exists()) Student_File.mkdir();
        if(!Teacher_File.exists()) Teacher_File.mkdir();
        if(!Room_File.exists()) Room_File.mkdir();

        SpringApplication.run(ELearningApplication.class, args);

    }

}

