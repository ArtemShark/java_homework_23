package com.company.homework_lesson_23.service;

import com.company.homework_lesson_23.dto.FlatDTO;
import com.company.homework_lesson_23.model.Flat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Optional;

public interface FlatService extends CRUDInterface<Flat> {
    Page<Flat> find(Pageable pageable);

    Optional<Flat> findFlatById(Long id);

    Page<Flat> findByFlatFilters(FlatDTO flatDTO);

    BigDecimal getAveragePrice();

    BigDecimal getMaximumPrice();

    BigDecimal getMinimumPrice();
}
