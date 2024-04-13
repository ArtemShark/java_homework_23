package com.company.homework_lesson_23.service;

import com.company.homework_lesson_23.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClientService extends CRUDInterface<Client> {
    Optional<Client> findClientById(long id);

    Page<Client> find(Pageable pageable);

    Optional<List<Client>> findClientsByFirstNameAndLastName(String firstName, String lastName);

    Optional<List<Client>> findClientsByNumberPhone(String numberPhone);

    Optional<List<Client>> findClientsByFlatId(long flatId);

    Optional<List<Client>> findClientsByRentingDateBeginDuringMonth();

    Optional<List<Client>> findClientsByRentingDateEndDuringMonth();

    Optional<List<Client>> findClientsByRentingDateRangeLessThanMonth();

    Optional<List<Client>> findClientsByRentingDateMoreThanYear();

}
