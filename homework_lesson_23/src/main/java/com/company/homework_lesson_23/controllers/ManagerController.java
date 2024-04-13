package com.company.homework_lesson_23.controllers;

import com.company.homework_lesson_23.model.Info;
import com.company.homework_lesson_23.model.Manager;
import com.company.homework_lesson_23.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;

    @GetMapping(value = "/managers")
    public ResponseEntity<List<Manager>> getAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        return new ResponseEntity<>(managerService.find(PageRequest.of(page, pageSize)).toList(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/manager/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
        try {
            managerService.delete(managerService.findManagerById(id));
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>("Manager not deleted!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Manager deleted!", HttpStatus.OK);
    }

    @PutMapping(value = "/manager/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id,
                                    @RequestBody Manager manager) {
        try {
            Manager current = managerService.findManagerById(id);

            current.setInfo(Info.gettingInfo(
                    manager.getInfo(),
                    current.getInfo()));

            return new ResponseEntity<>(managerService.update(current), HttpStatus.OK);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>("Incorrect imput!", HttpStatus.BAD_REQUEST);
        }
    }
}
