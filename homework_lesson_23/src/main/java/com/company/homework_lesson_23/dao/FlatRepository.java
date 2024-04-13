package com.company.homework_lesson_23.dao;


import com.company.homework_lesson_23.model.Flat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
@Transactional
public interface FlatRepository extends PagingAndSortingRepository<Flat, Long>, JpaRepository<Flat, Long> {
    String SELECT_FLATS_BY_FILTERS = """
        SELECT fl.*
        FROM flats fl JOIN managers man
        ON man.id=fl.manager_id
        WHERE (fl.number_room=:numberCount OR :numberCount IS NULL) AND
        (fl.area=:area OR :area IS NULL) AND
        (fl.price=CAST(:price AS MONEY) OR :price IS NULL) AND
        (man.first_name=:manFN OR :manFN IS NULL) AND
        (man.last_name=:manLN OR :manLN IS NULL)
    """;

    String SELECT_AVERAGE_PRICE = "SELECT AVG(CAST(price AS NUMERIC)) FROM flat";

    String SELECT_MAXIMUM_PRICE = "SELECT MAX(price) FROM flat";

    String SELECT_MINIMUM_PRICE = " SELECT MIN(price) FROM flat";

    Optional<Flat> findFlatById(Long id);

    @Query(value = SELECT_FLATS_BY_FILTERS, nativeQuery = true)
    Page<Flat> findByFlatFilters(@Param("numberCount") int numberCount,
                                           @Param("area") String area,
                                           @Param("price") BigDecimal price,
                                           @Param("manFN") String managerFirstName,
                                           @Param("manLN") String managerLastName,
                                           Pageable pageable);

    @Query(value = SELECT_AVERAGE_PRICE, nativeQuery = true)
    BigDecimal getAveragePrice();

    @Query(value = SELECT_MAXIMUM_PRICE, nativeQuery = true)
    BigDecimal getMaximumPrice();

    @Query(value = SELECT_MINIMUM_PRICE, nativeQuery = true)
    BigDecimal getMinimumPrice();
}
