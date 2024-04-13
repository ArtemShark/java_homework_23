package com.company.homework_lesson_23.service;

import java.util.List;

public interface CRUDInterface<T> {
    void delete(T object);
    List<T> saveList(List<T> objectList);
    T save(T object);
    List<T> find();
    T update(T object);
    void deleteList();
}
