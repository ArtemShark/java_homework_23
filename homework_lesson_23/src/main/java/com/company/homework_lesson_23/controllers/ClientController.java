package com.company.homework_lesson_23.controllers;


import com.company.homework_lesson_23.model.Client;
import com.company.homework_lesson_23.model.Info;
import com.company.homework_lesson_23.service.ClientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping(value = "/clients")
    public ResponseEntity<List<Client>> getAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                               @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        List<Client> clients = clientService.find(PageRequest.of(page, pageSize)).toList();

        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PostMapping(value = "/client")
    public ResponseEntity<?> insert(@RequestBody Client client) {

        return clientService.save(client) == null ?
                new ResponseEntity<>("Incorrect input!", HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(client, HttpStatus.OK);
    }

    @DeleteMapping(value = "/client/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
        try {
            clientService.delete(clientService.findClientById(id).orElseThrow());
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>("Incorrect Id!", HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>("Client not deleted", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Client deleted!", HttpStatus.OK);
    }

    @PutMapping(value = "/client/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id,
                                    @RequestBody Client client) {
        try {
            Optional<Client> currentClient = clientService.findClientById(id);

            if (currentClient.isPresent()) {
                currentClient.get().setInfo(Info.gettingInfo(
                        client.getInfo(),
                        currentClient.get().getInfo()));

                return new ResponseEntity<>(clientService.update(currentClient.get()), HttpStatus.OK);
            }

            throw new EntityNotFoundException();
        }
        catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Not exists!", HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>("Incorrect input!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/clients/searching/by-name")
    public ResponseEntity<?> findAllByFirstAndLastNames(@RequestParam(name = "firstName", defaultValue = "") String firstName,
                                                        @RequestParam(name = "lastName", defaultValue = "") String lastName) {
        try {
            if (!firstName.isEmpty() && !lastName.isEmpty()) {
                return new ResponseEntity<>(clientService.findClientsByFirstNameAndLastName(firstName, lastName).orElseThrow(), HttpStatus.OK);
            }

            throw new RuntimeException("Both parameters is empty!");
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>("Enter correct parameters!", HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/clients/searching/by-phone")
    public ResponseEntity<?> findAllByNumberPhone(@RequestParam(name = "numberPhone", defaultValue = "") String numberPhone) {
        try {
            if (!numberPhone.isEmpty()) {
                return new ResponseEntity<>(clientService.findClientsByNumberPhone(numberPhone).orElseThrow(), HttpStatus.OK);
            }

            throw new RuntimeException("Parameter is empty!");
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>("Enter correct parameters!", HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/clients/searching/by-flat")
    public ResponseEntity<?> findAllByRentalInquiries(@RequestParam(name = "flatId", defaultValue = "0") Long flatId) {
        try {
            if (flatId != 0) {
                return new ResponseEntity<>(clientService.findClientsByFlatId(flatId).orElseThrow(), HttpStatus.OK);
            }

            throw new NoSuchElementException();
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>("Enter correct parameters!", HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/clients/searching/by-rented-during-month")
    public ResponseEntity<?> findAllThatRentedFlatDuringMonth() {
        try {
            return new ResponseEntity<>(clientService.findClientsByRentingDateBeginDuringMonth().orElseThrow(), HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>("No clients that rented flats during this month!", HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/clients/searching/by-rent-expiring-during-month")
    public ResponseEntity<?> findAllWithRentExpiredDuringMonth() {
        try {
            return new ResponseEntity<>(clientService.findClientsByRentingDateEndDuringMonth().orElseThrow(), HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>("No clients with expiring rent during last month!", HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/clients/searching/by-rent-duration-less-month")
    public ResponseEntity<?> findAllWithRentDurationLessThanMonth() {
        try {
            return new ResponseEntity<>(clientService.findClientsByRentingDateRangeLessThanMonth().orElseThrow(), HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>("No clients that rented flats for less than month!", HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/clients/searching/by-rent-duration-more-year")
    public ResponseEntity<?> findAllWithRentDurationMoreThanYear() {
        try {
            return new ResponseEntity<>(clientService.findClientsByRentingDateMoreThanYear().orElseThrow(), HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>("No clients that rented flats for more than year!", HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
