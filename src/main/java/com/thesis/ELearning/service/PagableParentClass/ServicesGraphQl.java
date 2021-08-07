package com.thesis.ELearning.service.PagableParentClass;


import com.thesis.ELearning.entity.API.ApiSettings;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface  ServicesGraphQl<T> {
    List<T> data(String search, int page);

    T save(T t);

    boolean deleteById(String id);

    T findById(String id);

    ApiSettings apiSettings();

    long count();
}
