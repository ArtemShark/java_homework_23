package com.company.homework_lesson_23.dao;

import com.company.homework_lesson_23.model.FlatLeaseExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface FlatLeaseExperienceRepository extends PagingAndSortingRepository<FlatLeaseExperience, Long>,
        JpaRepository<FlatLeaseExperience, Long> {
    Optional<FlatLeaseExperience> findFlatLeaseExperienceById(Long id);
}
