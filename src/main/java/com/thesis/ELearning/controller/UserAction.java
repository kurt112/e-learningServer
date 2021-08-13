package com.thesis.ELearning.controller;

import com.thesis.ELearning.configuration.Login.AuthenticationRequest;
import com.thesis.ELearning.entity.Student;
import com.thesis.ELearning.entity.Teacher;
import com.thesis.ELearning.entity.User;
import com.thesis.ELearning.service.EmailSenderService;
import com.thesis.ELearning.service.MyUserDetailsService;
import com.thesis.ELearning.service.serviceImplementation.StudentService;
import com.thesis.ELearning.service.serviceImplementation.TeacherService;
import com.thesis.ELearning.service.serviceImplementation.UserService;
import com.thesis.ELearning.utils.FormattedDate;
import com.thesis.ELearning.utils.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

@RestController
public class UserAction {

    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService userDetailsService;
    private final Jwt jwt;
    private final UserService userService;
    private final StudentService studentService;
    private final TeacherService teacherService;

    @Autowired
    public UserAction(AuthenticationManager authenticationManager, MyUserDetailsService userDetailsService, Jwt jwt, UserService userService, StudentService studentService, TeacherService teacherService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwt = jwt;
        this.userService = userService;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @PostMapping("/re-login")
    public ResponseEntity<HashMap<?, ?>> ReLogin(@RequestParam("token") String token) {
        HashMap<String, Object> hashMap = new HashMap<>();

        String email = jwt.getUsername(token);

        User user = userService.findByEmail(email);

        hashMap.put("token", token);
        hashMap.put("message", "Login Successful");
        hashMap.put("user", user);
        return ResponseEntity.ok(hashMap);
    }

    @PostMapping("/login")
    public ResponseEntity<HashMap<?, ?>> Login(@RequestBody AuthenticationRequest authenticationRequest, @RequestHeader("remember") boolean remember) {

        HashMap<String, Object> hashMap = new HashMap<>();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()));
        } catch (Exception badCredentialsException) {
            hashMap.put("message", "Account Not Found");
            return ResponseEntity.badRequest().body(hashMap);
        }


        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = this.jwt.generateToken(userDetails, remember);
        hashMap.put("token", jwt);
        hashMap.put("message", "Login Successful");
        hashMap.put("user", userDetailsService.getUser());


        return ResponseEntity.ok(hashMap);
    }

    @PostMapping("/pre-register")
    public ResponseEntity<?> FindUserId(@RequestParam("id") String id) {
        Student student = studentService.findById(id);

        Teacher teacher = teacherService.findById(id);

        if (student != null) {

            if (student.getUser().isEnabled())
                return ResponseEntity.badRequest().body("Your Account Is Already Registered");

            return ResponseEntity.ok(student.getUser().getUserRole());
        }

        if (teacher != null) {

            if (teacher.getUser().isEnabled())
                return ResponseEntity.badRequest().body("Your Account Is Already Registered");

            return ResponseEntity.ok(teacher.getUser().getUserRole());
        }

        return ResponseEntity.badRequest().body("User Not Existing");
    }

    @PostMapping("/logoutUser")
    public ResponseEntity<?> LogoutUser(@RequestParam("token") String token) {
        jwt.removeToken(token);
        return ResponseEntity.ok("User Logout Success");
    }

    @PostMapping("/update-account")
    public ResponseEntity<?> UpdateUser(@RequestParam("email") String email,
                                        @RequestParam("firstName") String firstName,
                                        @RequestParam("lastName") String lastName,
                                        @RequestParam("picture") String picture,
                                        @RequestParam("birthdate") String birthdate,
                                        @RequestParam("id") String id,
                                        @RequestParam("password") String password,
                                        @RequestParam("gender") String gender,
                                        @RequestParam("middleName") String middleName
    ) {
        User user = userService.findById(id);
        Date date = FormattedDate.dateToString(birthdate);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPicture(picture);
        user.setPassword(password);
        user.setGender(gender);
        user.setMiddleName(middleName);

        if (date != null) user.setBirthdate(date);
        user.setEmail(email);


        userService.save(user);
        return ResponseEntity.ok("User Update Success");
    }

    @GetMapping("/user")
    public ResponseEntity<?> GetUser(@RequestParam("email") String email) {

        User user = userService.findByEmail(email);

        if (user != null) {
            return ResponseEntity.badRequest().body("User Is Not Null");
        }

        return ResponseEntity.ok("User Is Null");
    }

    @PostMapping("/forgot")
    public ResponseEntity<?> forgotPassword(@RequestParam("email") String email) {
        User user = userService.findByEmail(email);

        if (user == null) {
            return ResponseEntity.badRequest().body("Email Is Not Existing");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        final String token = this.jwt.generateTokenResetPassword(userDetails);

        StringBuilder builder = new StringBuilder();

        System.out.println(token);

        builder.append("<!doctype html>\n" +
                "<html lang=\"en\"\n" +
                "      dir=\"ltr\"\n" +
                "      xmlns=\"http://www.w3.org/1999/xhtml\"\n" +
                "      xmlns:th=\"http://www.thymeleaf.org\">\n" +
                "\n" +
                "<head>\n" +
                "  <meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />\n" +
                "  <title>Reset Password Email Template</title>\n" +
                "  <meta name=\"description\" content=\"Reset Password Email Template.\">\n" +
                "  <style type=\"text/css\">\n" +
                "    a:hover {text-decoration: underline !important;}\n" +
                "  </style>\n" +
                "</head>\n" +
                "\n" +
                "<body marginheight=\"0\" topmargin=\"0\" marginwidth=\"0\" style=\"margin: 0px; background-color: #f2f3f8;\" leftmargin=\"0\">\n" +
                "<!--100% body table-->\n" +
                "<table cellspacing=\"0\" border=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#f2f3f8\"\n" +
                "       style=\"@import url(https://fonts.googleapis.com/css?family=Rubik:300,400,500,700|Open+Sans:300,400,600,700); font-family: 'Open Sans', sans-serif;\">\n" +
                "  <tr>\n" +
                "    <td>\n" +
                "      <table style=\"background-color: #f2f3f8; max-width:670px;  margin:0 auto;\" width=\"100%\" border=\"0\"\n" +
                "             align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "        <tr>\n" +
                "          <td style=\"text-align:center;margin: auto;display: flex;justify-content: center;\">\n" +
                "            <a href=\"https://admiring-goldstine-7f4e6f.netlify.app\"\n" +
                "               style=\"height: 200px; width: 340px; display: block; background-position: center center;background: url('https://g-learn-files.s3.us-west-2.amazonaws.com/image_Email.png');background-repeat: no-repeat ;background-size: contain\" title=\"logo\" target=\"_blank\">\n" +
                "\n" +
                "            </a>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "\n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <table width=\"95%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                   style=\"max-width:670px;background:#fff; border-radius:3px; text-align:center;-webkit-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);-moz-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);box-shadow:0 6px 18px 0 rgba(0,0,0,.06);\">\n" +
                "              <tr>\n" +
                "                <td style=\"height:40px;\">&nbsp;</td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td style=\"padding:0 35px;\">\n" +
                "                  <h1 style=\"color:#1e1e2d; font-weight:500; margin:0;font-size:32px;font-family:'Rubik',sans-serif;\">You have\n" +
                "                    requested to reset your password</h1>\n" +
                "                  <span\n" +
                "                          style=\"display:inline-block; vertical-align:middle; margin:29px 0 26px; border-bottom:1px solid #cecece; width:100px;\"></span>\n" +
                "                  <p style=\"color:#455056; font-size:15px;line-height:24px; margin:0;\">\n" +
                "                    You have requested that your password be reset. If you did not make this request, simply ignore this email. Your password must be reset within 15 minutes of receipt of this email.\n" +
                "                    To reset your password , simply click the button below.\n" +
                "                  </p>\n" +
                "                   <a href=\"https://eellearning.herokuapp.com/reset-password?access="+token+"\"\n" +
                "                     style=\"background:#20e277;text-decoration:none !important; font-weight:500; margin-top:35px; color:#fff;text-transform:uppercase; font-size:14px;padding:10px 24px;display:inline-block;border-radius:50px;\">Reset\n" +
                "                    Password</a>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td style=\"height:40px;\">&nbsp;</td>\n" +
                "              </tr>\n" +
                "            </table>\n" +
                "          </td>\n" +
                "        <tr>\n" +
                "          <td style=\"height:20px;\">&nbsp;</td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "          <td style=\"text-align:center;\">\n" +
                "            <p style=\"font-size:14px; color:rgba(69, 80, 86, 0.7411764705882353); line-height:18px; margin:0 0 0;\">&copy; <strong>https://admiring-goldstine-7f4e6f.netlify.app</strong></p>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "          <td style=\"height:80px;\">&nbsp;</td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "</table>\n" +
                "<!--/100% body table-->\n" +
                "</body>\n" +
                "\n" +
                "</html>");

        EmailSenderService mailer = new EmailSenderService();

        try {
            mailer.sendEmail(email, "Reset Password", builder.toString());
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Failed to sent email.");
            ex.printStackTrace();
        }

        return ResponseEntity.ok("User Is Exist");
    }

    @PostMapping("/newPassword")
    public ResponseEntity<?> newPassword(@RequestParam("token") String token,
                                         @RequestParam("password") String password) {
        String email = jwt.getUsername(token);
        User user = userService.findByEmail(email);

        if (email == null) {
            return ResponseEntity.badRequest().body("User Is Not Existing");
        }

        user.setPassword(new BCryptPasswordEncoder().encode(password));
        userService.save(user);

        return ResponseEntity.ok("User Is Exist");
    }

}
