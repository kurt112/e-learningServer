package com.thesis.ELearning.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormattedDate {

    private static final FormattedDate single_instance =  new FormattedDate();
    private FormattedDate() {
    }

    public static FormattedDate getInstance() {

        return single_instance;
    }

    public   static String getDateNowWithTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        return myDateObj.format(myFormatObj);
    }

    public static String getDateNow(){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return myDateObj.format(myFormatObj);
    }
}
