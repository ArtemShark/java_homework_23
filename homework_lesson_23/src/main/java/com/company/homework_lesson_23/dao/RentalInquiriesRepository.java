package com.company.homework_lesson_23.dao;

import com.company.homework_lesson_23.model.RentalInquiries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RentalInquiriesRepository extends PagingAndSortingRepository<RentalInquiries, Long>,
        JpaRepository<RentalInquiries, Long> {
    boolean existsRentalInquiriesByFlatId(long flatId);
}