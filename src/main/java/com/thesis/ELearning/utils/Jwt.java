package com.thesis.ELearning.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;

@Service
public class Jwt {
    private String SECRET_KEY = "secret";

    public String getUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

//    public Date getExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {

        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

//    public Boolean isTokenExpired(String token) {
//        return getExpiration(token).before(new Date());
//    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsername(token);
        return (username.equals(userDetails.getUsername()));
    }

    public Boolean removeToken(String token){
        Claims claims = extractAllClaims(token);
        claims.clear();

        return true;
    }

    public Date extendDay(int day){
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DATE, day);


        return calendar.getTime();
    }
}
