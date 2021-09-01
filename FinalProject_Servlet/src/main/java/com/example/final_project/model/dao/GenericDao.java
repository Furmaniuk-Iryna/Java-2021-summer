package com.example.final_project.model.dao;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public interface GenericDao<T> extends AutoCloseable {
    void save(T entity);
    T findById(long id);
    T findByName(String name);
    List<T> findAll();
    void update(T entity);
    void close();
}
