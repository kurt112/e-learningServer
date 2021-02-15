package com.thesis.ELearning.controller.AdminRest;

import com.thesis.ELearning.entity.*;
import com.thesis.ELearning.entity.API.PagesContent;
import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.service.serviceImplementation.*;
import com.thesis.ELearning.utils.FormattedDate;
import com.thesis.ELearning.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

@RestController
@RequestMapping("/admin/activity")
public class ActivityAdmin {

    private final StorageService storageService;
    private final SubjectService subjectService;
    private final RoomShiftService roomShiftService;
    private final ActivityService activityService;
    private final RoomClassesService roomClassesService;
    private final RoomClassesActivityService roomClassesActivityService;


    @Autowired
    public ActivityAdmin(StorageService storageService, SubjectService subjectService, RoomShiftService roomShiftService, ActivityService activityService, RoomClassesService roomClassesService, RoomClassesActivityService roomClassesActivityService) {
        this.storageService = storageService;
        this.subjectService = subjectService;
        this.roomShiftService = roomShiftService;
        this.activityService = activityService;
        this.roomClassesService = roomClassesService;
        this.roomClassesActivityService = roomClassesActivityService;
    }


    @PostMapping("/upload")
    public ResponseEntity<Response<HashMap>> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("activity-name") String activityName,
                                                              @RequestParam("shift-id") int id, @RequestParam("subject-code") String subjectCode,
                                                              @RequestParam("activity-type") String activityType,
                                                              @RequestParam("deadline-date") String deadlineDate, @RequestParam("deadline-time") String deadlineTime,
                                                              @RequestParam("activity-description") String description) {

        HashMap<String, String> hashMap = new HashMap();
        Subject subject = subjectService.findById(subjectCode);
        RoomShift roomShift = roomShiftService.findById(String.valueOf(id));
        RoomClass roomClasses = roomClassesService.FindRoomClassByShiftAndSubject(roomShift.getId(), subject.getSubjectCode());

        try {
            String path = storageService.UploadActivityClass(file, subject, roomShift, activityName, activityType);
            Activity activity = new Activity(0, activityName, path, FormattedDate.getDateNow(), deadlineDate + " "
                    + deadlineTime, activityType, description, "To Review");
            RoomClassesActivity roomClassesActivity = new RoomClassesActivity(0, roomClasses, activity);

            if (!activityService.save(activity)) {
                System.out.println("asdasd");
            }

            roomClassesActivityService.save(roomClassesActivity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(
                new Response<>("File Upload Successful", hashMap), HttpStatus.OK
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


    @GetMapping("/list")
    public PagesContent<RoomClassesActivity> activityList(@RequestParam("search") String search, @RequestParam("page") int page) {
        Page<RoomClassesActivity> current = roomClassesActivityService.data(search, page);
        System.out.println(Arrays.toString(current.getContent().toArray()));
        return new PagesContent<>(current.getContent(), current.getTotalElements(), current.getTotalPages(), current.getNumber());
    }
}

