package com.thesis.ELearning.service;

import java.util.List;

public interface ServicesStringID<T>{
    List<T> findAll();

    T findById(String id);

    boolean save(T t);

    boolean deleteById(String id);

    boolean delete(T t);

}
