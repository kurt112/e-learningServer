package com.thesis.ELearning.entity.API;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PagesContent<T> {
    private List<T> items;
    private long totalItems;
    private int totalPages;
    private int currentPage;
}
