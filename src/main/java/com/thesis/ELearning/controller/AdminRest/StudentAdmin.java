package com.thesis.ELearning.controller.AdminRest;

import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.entity.RoomShift;
import com.thesis.ELearning.entity.Student;
import com.thesis.ELearning.entity.User;
import com.thesis.ELearning.service.EmailService.EmailSenderService;
import com.thesis.ELearning.service.EmailService.EmailType;
import com.thesis.ELearning.service.MyUserDetailsService;
import com.thesis.ELearning.service.serviceImplementation.StudentService;
import com.thesis.ELearning.service.serviceImplementation.UserService;
import com.thesis.ELearning.utils.Jwt;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

@RestController
public class StudentAdmin {

    final private StudentService studentService;
    final private UserService userService;
    final private Jwt jwt;
    final private MyUserDetailsService userDetailsService;


    @Autowired

    public StudentAdmin(StudentService studentService, UserService userService, Jwt jwt, MyUserDetailsService userDetailsService) {
        this.studentService = studentService;
        this.userService = userService;
        this.jwt = jwt;
        this.userDetailsService = userDetailsService;
    }


    @PostMapping("/student-register")
    public ResponseEntity<Response<Student>> student(@RequestParam("id") String id) {

        if (studentService.findById(id) != null) {
            return new ResponseEntity<>(
                    new Response<>("Student is already exist", null),
                    HttpStatus.BAD_REQUEST
            );
        }

        User user = new User("" + id, "?", "?", "?", "?", "", "", "", new Date(), "STUDENT", false, false, false, false, new Date(), new Date());

        Student student = new Student(id, user);

        userService.save(user);
        studentService.save(student);

        return new ResponseEntity<>(
                new Response<>("Register Student Success", student),
                HttpStatus.OK
        );
    }

    @PostMapping("/student-fillUp")
    public ResponseEntity<Response<Student>> updateUser(@RequestParam("id") String id,
                                                        @RequestParam("first-name") String firstName,
                                                        @RequestParam("middle-name") String middleName,
                                                        @RequestParam("last-name") String lastName,
                                                        @RequestParam("suffix") String suffix,
                                                        @RequestParam("birth-date") String birthDate,
                                                        @RequestParam("gender") String gender,
                                                        @RequestParam("email") String email,
                                                        @RequestParam("password") String password) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = formatter.parse(birthDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        user.setSuffix(suffix);
        user.setGender(gender);
        user.setPassword(password);
        user.setAccountNotExpired(true);
        user.setCredentialNotExpired(true);
        user.setAccountNotLocked(true);
        user.setUserRole("STUDENT");

        user.setBirthdate(date);
        Student student = new Student(id, user);


        Thread thread = new Thread(() -> {
            EmailSenderService mailer = new EmailSenderService();


            final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            final String token = this.jwt.generateToken(userDetails, false);

            try {
                mailer.sendEmail(email, "Verify Email", EmailType.userVerify(token));
                System.out.println("Email sent.");
            } catch (Exception ex) {
                System.out.println("Failed to sent email.");
                ex.printStackTrace();
            }
        });

        thread.start();


        userService.save(user);
        studentService.save(student);


        return new ResponseEntity<>(
                new Response<>("Register Student Success", null),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/student")
    public ResponseEntity<Response<?>> deleteStudent(@RequestParam("id") String id) {

        Student student = studentService.findById(id);
        if (student == null) {
            return new ResponseEntity<>(
                    new Response<>("Student Id Is Not Existing", null),
                    HttpStatus.BAD_REQUEST
            );
        }
        String email = student.getUser().getEmail();
        studentService.deleteById(id);
//        userService.deleteById(email);
        return new ResponseEntity<>(
                new Response<>("Delete Student Success", "Student Delete Success"),
                HttpStatus.OK
        );
    }

    @PostMapping("/off/student")
    public ResponseEntity<Response<?>> setStudentOff(@RequestParam("id") String id) {

        Student student = studentService.findById(id);
        student.setStatus(0);
        studentService.save(student);

        return new ResponseEntity<>(
                new Response<>("Student On", "Student On"),
                HttpStatus.OK
        );
    }

    @PostMapping("/on/student")
    public ResponseEntity<Response<?>> setStudentOn(@RequestParam("id") String id) {

        Student student = studentService.findById(id);
        student.setStatus(1);
        studentService.save(student);

        return new ResponseEntity<>(
                new Response<>("Student Off", "Student Off"),
                HttpStatus.OK
        );
    }

    @PostMapping("/upload/csv")
    public ResponseEntity<Response<?>> uploadCSCV(@RequestParam("file") MultipartFile file) throws IOException {

//        Student student  = studentService.findById(id);
//        student.setStatus(1);
//        studentService.save(student);

        System.out.println(file.getResource());
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getContentType());
        XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());

        XSSFSheet sheet = wb.getSheetAt(0);
        Iterator<Row> itr = sheet.iterator();    //iterating over excel file
        itr.next();
        while (itr.hasNext()) {
            Row row = itr.next();

            int lrn = (int) row.getCell(0).getNumericCellValue();
            String firstName = row.getCell(1).getStringCellValue();
            String lastName = row.getCell(2).getStringCellValue();
            String email = row.getCell(3).getStringCellValue();
            String password = row.getCell(4).getStringCellValue();
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            password = bCryptPasswordEncoder.encode(password);

            User user = new User();
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setMiddleName("");
            user.setLastName(lastName);
            user.setSuffix("");
            user.setGender("?");
            user.setPassword(password);
            user.setAccountNotExpired(true);
            user.setCredentialNotExpired(true);
            user.setAccountNotLocked(true);
            user.setUserRole("STUDENT");

            user.setBirthdate(new Date());
            Student student = new Student("" + lrn, user);

            userService.save(user);
            studentService.save(student);

            EmailSenderService mailer = new EmailSenderService();


            final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            final String token = this.jwt.generateToken(userDetails, false);

            try {
                mailer.sendEmail(email, "Verify Email", EmailType.userVerify(token));
                System.out.println("Email sent.");
            } catch (Exception ex) {
                System.out.println("Failed to sent email.");
                ex.printStackTrace();
            }

        }

        return new ResponseEntity<>(
                new Response<>("Student Off", "Student Off"),
                HttpStatus.OK
        );
    }


}
