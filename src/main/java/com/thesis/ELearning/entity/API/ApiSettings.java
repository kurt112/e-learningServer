package com.thesis.ELearning.entity.API;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiSettings {
    private long totalElements;
    private int totalPages;
    private int currentPage;
}
