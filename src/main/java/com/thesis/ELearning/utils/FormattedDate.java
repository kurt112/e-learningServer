package com.thesis.ELearning.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class FormattedDate {

    private static final FormattedDate single_instance =  new FormattedDate();
    private static final String dateFormat = "MMMM dd yyyy";
    private static final String dateTimeFormat = "dd/MM/yyyy HH-mm a";
    private static final DateTimeFormatter toDateTime = DateTimeFormatter.ofPattern(dateTimeFormat);
    private static final DateTimeFormatter toDate = DateTimeFormatter.ofPattern(dateFormat);
    private FormattedDate() {
    }

    public static FormattedDate getInstance() {

        return single_instance;
    }

    public static String getDateNowWithTime() {
        return  toDateTime.format(LocalDateTime.now());
    }

    public static String getDateNow(){
        return toDate.format(LocalDate.now());
    }

    public static String parseDateNow(String date){
        if(date.trim().isEmpty()) return "";


        return toDate.parse(date).toString();
    }

    public static String parseTimeNow(String time) {
        if(time.trim().isEmpty()) return "";

        return toDateTime.parse(time).toString();
    }



}