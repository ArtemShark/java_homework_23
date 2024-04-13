package com.company.homework_lesson_23.service;

import com.company.homework_lesson_23.model.Manager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ManagerService extends CRUDInterface<Manager> {
    Page<Manager> find(Pageable pageable);

    Manager findManagerById(Long id);
}
