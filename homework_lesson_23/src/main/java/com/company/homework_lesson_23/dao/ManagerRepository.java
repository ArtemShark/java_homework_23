package com.company.homework_lesson_23.dao;

import com.company.homework_lesson_23.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ManagerRepository extends PagingAndSortingRepository<Manager, Long>, JpaRepository<Manager, Long> {
    Manager findManagerById(Long id);
}
