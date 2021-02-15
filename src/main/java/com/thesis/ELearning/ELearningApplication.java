package com.thesis.ELearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class ELearningApplication {

    public static void main(String[] args) {


        File AllDataContainer = new File("C:\\E-learning");

        if(!AllDataContainer.exists()) AllDataContainer.mkdir();

        String parentPath = AllDataContainer.getPath();
        File Student_File = new File(parentPath+"\\Student");
        File Teacher_File = new File(parentPath+ "\\Teacher");
        File Room_File = new File(parentPath+"\\Room");

        if(!Student_File.exists()) Student_File.mkdir();
        if(!Teacher_File.exists()) Teacher_File.mkdir();
        if(!Room_File.exists()) Room_File.mkdir();

        SpringApplication.run(ELearningApplication.class, args);

    }

}

