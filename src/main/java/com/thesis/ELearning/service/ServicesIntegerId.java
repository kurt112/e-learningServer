package com.thesis.ELearning.service;

import java.util.List;

public interface  ServicesIntegerId <T>{
    List<T> findAll();

    T findById(int id);

    boolean save(T t);

    boolean deleteById(int id);

    boolean delete(T t);
}
