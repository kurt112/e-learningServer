package com.thesis.ELearning.service.PagableParentClass;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ServicePageable<T>{

    Page<T> data(String search, int page);

    boolean save(T t);

    boolean deleteById(String id);

    T findById(String id);


}
