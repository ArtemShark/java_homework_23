package com.company.homework_lesson_23.dao;

import com.company.homework_lesson_23.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ClientRepository extends PagingAndSortingRepository<Client, Long>, JpaRepository<Client, Long> {

    String SELECT_ALL_CLIENTS_BY_FIRST_AND_LAST_NAMES = "SELECT * FROM clients WHERE first_name=:firstName AND last_name=:lastName";

    String SELECT_ALL_CLIENTS_BY_NUMBER_PHONE = "SELECT * FROM clients WHERE number_phone=:numberPhone";

    String SELECT_ALL_CLIENTS_BY_FLAT_ID = "SELECT c.* FROM clients c JOIN rental_inquiries rra ON c.id=rra.client_id JOIN flat a ON a.id=rra.flat_id WHERE rra.flat_id=:flatId";

    String SELECT_ALL_CLIENTS_BY_RENTING_DATE_START_MONTH = "SELECT c.* FROM lease_experience rh JOIN clients c ON c.id=rh.client_id WHERE rh.rent_start BETWEEN CURRENT_DATE - INTERVAL '1 MONTH' AND CURRENT_DATE";

    String SELECT_ALL_CLIENTS_BY_RENTING_DATE_FINISH_MONTH = "SELECT c.* FROM lease_experience rh JOIN clients c ON c.id=rh.client_id WHERE rh.rent_finish BETWEEN CURRENT_DATE AND CURRENT_DATE + INTERVAL '1 MONTH'";

    String SELECT_ALL_CLIENTS_BY_RENTING_DATE_RANGE_LESS_THAN_MONTH = "SELECT c.* FROM lease_experience rh JOIN clients c ON c.id=rh.client_id WHERE (rh.rent_finish - rh.rent_start) < 30";

    String SELECT_ALL_CLIENTS_BY_RENTING_DATE_MORE_THAN_YEAR = "SELECT c.* FROM lease_experience rh JOIN clients c ON c.id=rh.client_id WHERE (EXTRACT(YEAR FROM rh.rent_finish) - EXTRACT(YEAR FROM rh.rent_start)) > 1";

    Optional<Client> findClientById(Long id);

    @Query(value = SELECT_ALL_CLIENTS_BY_FIRST_AND_LAST_NAMES, nativeQuery = true)
    Optional<List<Client>> findClientsByFirstNameAndLastName(@Param("firstName") String firstName,
                                                             @Param("lastName") String lastName);

    @Query(value = SELECT_ALL_CLIENTS_BY_NUMBER_PHONE, nativeQuery = true)
    Optional<List<Client>> findClientsByNumberPhone(@Param("numberPhone") String numberPhone);

    @Query(value = SELECT_ALL_CLIENTS_BY_FLAT_ID, nativeQuery = true)
    Optional<List<Client>> findClientsByFlatId(@Param("flatId") long flatId);

    @Query(value = SELECT_ALL_CLIENTS_BY_RENTING_DATE_START_MONTH, nativeQuery = true)
    Optional<List<Client>> findClientsByRentingDateBeginDuringMonth();

    @Query(value = SELECT_ALL_CLIENTS_BY_RENTING_DATE_FINISH_MONTH, nativeQuery = true)
    Optional<List<Client>> findClientsByRentingDateEndDuringMonth();

    @Query(value = SELECT_ALL_CLIENTS_BY_RENTING_DATE_RANGE_LESS_THAN_MONTH, nativeQuery = true)
    Optional<List<Client>> findClientsByRentingDateRangeLessThanMonth();

    @Query(value = SELECT_ALL_CLIENTS_BY_RENTING_DATE_MORE_THAN_YEAR, nativeQuery = true)
    Optional<List<Client>> findClientsByRentingDateMoreThanYear();

}