package com.company.homework_lesson_23.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Log4j2
@Service
@RequiredArgsConstructor
public class RentInitializer {
    private final RentDBInitializer rentDBInitializer;

    public void initDB() {
        try {
            rentDBInitializer.deleteAllRows();

            rentDBInitializer.createRandomMangers(5);
            rentDBInitializer.createRandomClients(5);
            rentDBInitializer.createRandomFlats(4, 7, 1000.0);
            rentDBInitializer.createRandomFlatLeaseExperience();
            rentDBInitializer.createRandomRentalInquiries(5);
        }
        catch (RuntimeException | IOException e) {
            log.warn(e.getMessage());
        }
    }
}

