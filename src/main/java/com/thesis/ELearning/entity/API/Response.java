package com.thesis.ELearning.entity.API;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
public @Data class Response <T> {
    public String message;
    public T item;
}
