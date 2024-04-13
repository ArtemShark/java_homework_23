package com.company.homework_lesson_23.service;

import com.company.homework_lesson_23.model.FlatLeaseExperience;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface FlatLeaseExperienceService extends CRUDInterface<FlatLeaseExperience> {
    Page<FlatLeaseExperience> find(Pageable pageable);

    Optional<FlatLeaseExperience> findFlatLeaseExperienceById(Long id);
}