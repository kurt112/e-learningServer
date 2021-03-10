package com.thesis.ELearning.controller;

import com.thesis.ELearning.configuration.Login.AuthenticationRequest;
import com.thesis.ELearning.entity.User;
import com.thesis.ELearning.service.MyUserDetailsService;
import com.thesis.ELearning.service.serviceImplementation.UserService;
import com.thesis.ELearning.utils.Jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

@RestController
public class UserAction {

    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService userDetailsService;
    private final Jwt jwt;
    private final UserService userService;

    @Autowired
    public UserAction(AuthenticationManager authenticationManager, MyUserDetailsService userDetailsService, Jwt jwt, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwt = jwt;
        this.userService = userService;
    }

    @PostMapping("/re-login")
    public ResponseEntity<HashMap<?,?>> ReLogin(@RequestParam("token") String token){
       HashMap<String, Object> hashMap = new HashMap<>();

        String email = jwt.getUsername(token);

        User user = userService.findByEmail(email);

        hashMap.put("token", token);
        hashMap.put("message", "Login Successful");
        hashMap.put("user",user);
        return ResponseEntity.ok(hashMap);
    }

    @PostMapping("/login")
    public ResponseEntity<HashMap<?,?>> Login(@RequestBody AuthenticationRequest authenticationRequest) {
        System.out.println(authenticationRequest.getUsername());
        System.out.println(authenticationRequest.getPassword());
        HashMap<String, Object> hashMap = new HashMap<>();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()));
        }catch (Exception badCredentialsException){
            hashMap.put("message", "Account Not Found");

            return ResponseEntity.badRequest().body(hashMap);
        }


        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = this.jwt.generateToken(userDetails);
//        final String expiration  = this.jwt.getExpiration(jwt).toString();


//        hashMap.put("expiration", expiration);
        hashMap.put("token", jwt);
        hashMap.put("message", "Login Successful");
        hashMap.put("user",userDetailsService.getUser());


        return ResponseEntity.ok(hashMap);
    }

    @PostMapping("pre-register")
    public ResponseEntity<?> FindUserId (@RequestParam("id") String id) {
        User user  = userService.findById(id);

        if(user != null) return ResponseEntity.ok(user.getUserRole());

        return ResponseEntity.badRequest().body("User Not Existing");
    }

}
