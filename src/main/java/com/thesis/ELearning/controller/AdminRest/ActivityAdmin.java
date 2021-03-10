package com.thesis.ELearning.controller.AdminRest;

import com.thesis.ELearning.entity.*;
import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.service.serviceImplementation.*;
import com.thesis.ELearning.utils.FormattedDate;
import com.thesis.ELearning.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/admin/activity")
public class ActivityAdmin {

    private final StorageService storageService;
    private final SubjectService subjectService;
    private final RoomShiftService roomShiftService;
    private final ActivityService activityService;
    private final RoomShiftClassesService roomShiftClassesService;
    private final RoomShiftClassesActivityService roomShiftClassesActivityService;


    @Autowired
    public ActivityAdmin(StorageService storageService, SubjectService subjectService, RoomShiftService roomShiftService, ActivityService activityService, RoomShiftClassesService roomShiftClassesService, RoomShiftClassesActivityService roomShiftClassesActivityService) {
        this.storageService = storageService;
        this.subjectService = subjectService;
        this.roomShiftService = roomShiftService;
        this.activityService = activityService;
        this.roomShiftClassesService = roomShiftClassesService;
        this.roomShiftClassesActivityService = roomShiftClassesActivityService;
    }


    @PostMapping("/upload")
    public ResponseEntity<Response<RoomShiftClassesActivity>> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("activity-name") String activityName,
                                                                               @RequestParam("shift-id") int id, @RequestParam("subject-code") String subjectCode,
                                                                               @RequestParam("activity-type") String activityType,
                                                                               @RequestParam("deadline-date") String deadlineDate, @RequestParam("deadline-time") String deadlineTime,
                                                                               @RequestParam("activity-description") String description) {


        Subject subject = subjectService.findById(subjectCode);
        RoomShift roomShift = roomShiftService.findById(String.valueOf(id));

        RoomShiftClass roomShiftClasses = roomShiftClassesService.FindRoomClassByShiftAndSubject(roomShift.getId(), subject.getSubjectCode());
        System.out.println(roomShift.toString());
        System.out.println(roomShiftClasses.getId());
        try {
            String path = storageService.UploadActivityClass(file, subject, roomShift, activityName, activityType);
            Activity activity = new Activity(0, activityName, path, FormattedDate.getDateNow(), deadlineDate + " "
                    + deadlineTime, activityType, description, "To Review");
            RoomShiftClassesActivity roomShiftClassesActivity = new RoomShiftClassesActivity(0, roomShiftClasses, activity);

            if (!activityService.save(activity)) {
                System.out.println("asdasd");
            }
            roomShiftClassesActivityService.save(roomShiftClassesActivity);
            return new ResponseEntity<>(
                    new Response<>("File Upload Successful", roomShiftClassesActivity), HttpStatus.OK
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(
                new Response<>("File Upload Not Successful", null), HttpStatus.BAD_REQUEST
        );

    }


    @GetMapping("/download")
    public ResponseEntity<Object> downloadFile(@RequestParam("activity-id") String id) throws IOException {
        String filename = activityService.findById(id).getLink();
        File file = new File(filename);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition",
                String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok().headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/txt")).body(resource);
    }

    @GetMapping(value = "view",
            produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody InputStreamResource getImage(@RequestParam("activity-id") String id) throws IOException {

        Activity activity = activityService.findById(id);



        File file = new File(activity.getLink());
        if(file.exists()){
             return new InputStreamResource(new FileInputStream(file));
        }

        return null;
    }
}

