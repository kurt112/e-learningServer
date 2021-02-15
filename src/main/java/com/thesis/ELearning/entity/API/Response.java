package com.thesis.ELearning.entity.API;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;


@AllArgsConstructor
public @Data class Response <T> {
    public String message;
    public T item;


}
