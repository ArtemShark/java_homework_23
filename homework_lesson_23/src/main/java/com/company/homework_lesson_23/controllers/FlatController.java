package com.company.homework_lesson_23.controllers;


import com.company.homework_lesson_23.dto.FlatDTO;
import com.company.homework_lesson_23.model.Flat;
import com.company.homework_lesson_23.service.FlatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class FlatController {
    final private FlatService flatService;

    @GetMapping(value = "/flats")
    public ResponseEntity<List<Flat>> getAll(FlatDTO flatDTO) {
        List<Flat> flats;
        FlatDTO emptyDto = new FlatDTO();

        flats = (flatDTO.equals(emptyDto) ?
                flatService.find(PageRequest.of(flatDTO.getPage(), flatDTO.getPageSize())).toList() :
                flatService.findByFlatFilters(flatDTO).toList());

        return new ResponseEntity<>(flats, HttpStatus.OK);
    }

    @DeleteMapping(value = "/flat/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
        try {
            flatService.delete(flatService.findFlatById(id).orElseThrow());
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>("Incorrect Id", HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>("Flat not deleted!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Flat deleted!", HttpStatus.OK);
    }

    @PutMapping(value = "/flat/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id,
                                    @RequestBody Flat flat) {
        try {
            Flat current = flatService.findFlatById(id).orElseThrow();

            return new ResponseEntity<>(flatService.update(extractFlatData(flat, current)), HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>("Incorrect Id!", HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>("Incorrect input!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/apartment/searching/average-price")
    public ResponseEntity<?> findAveragePrice() {
        try {
            return new ResponseEntity<>(flatService.getAveragePrice(), HttpStatus.OK);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/apartment/searching/maximum-price")
    public ResponseEntity<?> findMaximumPrice() {
        try {
            return new ResponseEntity<>(flatService.getMaximumPrice(), HttpStatus.OK);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/apartment/searching/minimum-price")
    public ResponseEntity<?> findMinimumPrice() {
        try {
            return new ResponseEntity<>(flatService.getMinimumPrice(), HttpStatus.OK);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private static Flat extractFlatData(Flat curr, Flat prev) {
        if (curr.getNumberCount() > 0) {
            prev.setNumberCount(curr.getNumberCount());
        }
        if (curr.getArea() != null && !curr.getArea().isEmpty()) {
            prev.setArea(curr.getArea());
        }
        if (curr.getPrice() != null && curr.getPrice().compareTo(BigDecimal.ZERO) > 0) {
            prev.setPrice(curr.getPrice());
        }

        return prev;
    }
}

