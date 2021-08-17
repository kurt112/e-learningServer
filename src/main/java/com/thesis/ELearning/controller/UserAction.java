package com.thesis.ELearning.controller;

import com.thesis.ELearning.configuration.Login.AuthenticationRequest;
import com.thesis.ELearning.entity.API.Response;
import com.thesis.ELearning.entity.Student;
import com.thesis.ELearning.entity.Teacher;
import com.thesis.ELearning.entity.User;
import com.thesis.ELearning.service.EmailService.EmailSenderService;
import com.thesis.ELearning.service.EmailService.EmailType;
import com.thesis.ELearning.service.MyUserDetailsService;
import com.thesis.ELearning.service.serviceImplementation.StudentService;
import com.thesis.ELearning.service.serviceImplementation.TeacherService;
import com.thesis.ELearning.service.serviceImplementation.UserService;
import com.thesis.ELearning.utils.FormattedDate;
import com.thesis.ELearning.utils.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

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

    @PostMapping("/admin")
    public ResponseEntity<Response<?>> addAdmin(@RequestParam("email") String email){
        User user = userService.findByEmail(email);

        if(user != null){
            return  ResponseEntity
                    .badRequest()
                    .body(new Response<>("Admin is already exist", null));
        }

        Thread thread = new Thread(() ->{
            EmailSenderService mailer = new EmailSenderService();

            final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            final String token = this.jwt.generateToken(userDetails,false);

            try {
                mailer.sendEmail(email, "Verify Email", EmailType.userVerify(token));
            } catch (Exception ex) {
                System.out.println("Failed to sent email.");
            }
        });


        thread.start();

         user = new User(email,"?","?","?", "?","?","",
                "$2a$12$9aNZQjnLmTlBtYMVY0JtF.HXt3.pN8YxwxhSCH/cxcJFm2/VlDuWC",new Date(),
                "ADMIN",true,true,true,false,new Date(),new Date());

        userService.save(user);
        return new ResponseEntity<>(
                new Response<>("Register Admin is Success", "Success"),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/admin")
    public ResponseEntity<Response<?>> deleteAdmin(@RequestParam("email") String email){
        User user = userService.findByEmail(email);

        System.out.println("The email " + email);

        if(user == null|| !user.getUserRole().equals("ADMIN")){
            return new ResponseEntity<>(
                        new Response<>("Admin Is Not Existing", null),
                    HttpStatus.BAD_REQUEST
            );
        }

        userService.deleteById(""+user.getId());


        return new ResponseEntity<>(
                new Response<>("Delete Teacher Success", "Success"),
                HttpStatus.OK
        );
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
        } catch (BadCredentialsException badCredentialsException) {
            hashMap.put("message", "Account Not Found");
            return ResponseEntity.badRequest().body(hashMap);
        }catch (DisabledException disabledException){
            hashMap.put("message", "Please Verify Your Email First");
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
        Student student = studentService.getStudentById(id);

        Teacher teacher = teacherService.getTeacherById(id);

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

        Thread thread = new Thread(() -> {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            final String token = this.jwt.generateTokenResetPassword(userDetails);

            EmailSenderService mailer = new EmailSenderService();

            try {
                mailer.sendEmail(email, "Reset Password", EmailType.passwordReset(token));
                System.out.println("Email sent.");
            } catch (Exception ex) {
                System.out.println("Failed to sent email.");
                ex.printStackTrace();
            }

        });

        thread.start();

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

    @PostMapping("/verify")
    public ResponseEntity<?> verifyEmail(@RequestParam("token") String token){
        String email = jwt.getUsername(token);
        User user = userService.findByEmail(email);

        if (email == null)
            return ResponseEntity.badRequest().body("User Is Not Existing");


        user.setEnabled(true);
        userService.save(user);

        return ResponseEntity.ok("User Verified Successful");
    }

}
