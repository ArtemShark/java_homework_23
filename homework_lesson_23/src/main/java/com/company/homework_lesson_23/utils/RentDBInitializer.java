package com.company.homework_lesson_23.utils;


import com.company.homework_lesson_23.model.*;
import com.company.homework_lesson_23.service.RentDBInitService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Log4j2
@Service
@RequiredArgsConstructor
public class RentDBInitializer {
    @Value("${data.first_names}")
    private String pathToFirstNames;

    @Value("${data.last_names}")
    private String pathToLastNames;

    private static List<String> firstNames;
    private static List<String> lastNames;
    private final TxtFileReader txtFileReader;
    private final RentDBInitService rentDBInitService;

    @PostConstruct
    public void init() {
        try {
            txtFileReader.setFileName(pathToFirstNames);
            firstNames = txtFileReader.readFile();
            txtFileReader.setFileName(pathToLastNames);
            lastNames = txtFileReader.readFile();
        }
        catch (IOException e) {
            log.warn(e.getMessage());
        }
    }

    public void deleteAllRows() {
        rentDBInitService.deleteAllRowsInDB();
        log.debug("All rows was erased!");
    }

    public void createRandomClients(int countOfRecords) throws IOException {
        List<Client> clients = new ArrayList<>();

        for (int i = 0; i < countOfRecords; i++) {
            clients.add(Client
                    .builder()
                    .info(createRandomInfo())
                    .build());
        }

        rentDBInitService.saveClients(clients);
    }

    public void createRandomMangers(int countOfRecords) throws IOException {
        List<Manager> managers = new ArrayList<>();

        for (int i = 0; i < countOfRecords; i++) {
            managers.add(Manager
                    .builder()
                    .info(createRandomInfo())
                    .build());
        }

        rentDBInitService.saveManagers(managers);
    }

    public void createRandomFlats(int maxRooms, int countOfRecords, double maxPrice) {
        final int MIN_ROOMS = 1;
        final BigDecimal MAX_PRICE = BigDecimal.valueOf(maxPrice);
        final BigDecimal MIN_PRICE = BigDecimal.valueOf(20.5);
        List<Manager> managers = rentDBInitService.findAllManagers();
        List<Flat> flats = new ArrayList<>();

        for (int i = 0; i < countOfRecords; i++) {
            flats.add(Flat
                    .builder()
                    .numberCount(ThreadLocalRandom.current().nextInt(MIN_ROOMS ,maxRooms))
                    .area("Area №" + ThreadLocalRandom.current().nextInt(100))
                    .price(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble()).multiply(MAX_PRICE.subtract(MIN_PRICE)))
                    .manager(managers.get(ThreadLocalRandom.current().nextInt(managers.size())))
                    .build());
        }

        rentDBInitService.saveManagers(managers);
    }

    public void createRandomFlatLeaseExperience() {
        int cycleCount;
        final int DAYS_IN_YEAR = 365;
        List<Client> clients = rentDBInitService.findAllClients();
        List<Flat> flats = rentDBInitService.findAllFlats();
        List<FlatLeaseExperience> flatLeaseExperiences = new ArrayList<>();

        flatLeaseExperiences.add(FlatLeaseExperience
                .builder()
                .rentStart(Date.valueOf(LocalDate.now()))
                .rentFinish(Date.valueOf(LocalDate.now().plusMonths(1)))
                .client(clients.remove(ThreadLocalRandom.current().nextInt(clients.size())))
                .flat(flats.remove(ThreadLocalRandom.current().nextInt(flats.size())))
                .build());

        flatLeaseExperiences.add(FlatLeaseExperience
                .builder()
                .rentStart(Date.valueOf(LocalDate.now()))
                .rentFinish(Date.valueOf(LocalDate.now().plusDays(ThreadLocalRandom.current().nextInt(5, 20))))
                .client(clients.remove(ThreadLocalRandom.current().nextInt(clients.size())))
                .flat(flats.remove(ThreadLocalRandom.current().nextInt(flats.size())))
                .build());

        cycleCount = clients.size();

        for (int i = 0; i < cycleCount; i++) {
            if (flats.isEmpty()) {
                break;
            }

            int randomDays = ThreadLocalRandom.current().nextInt(10, DAYS_IN_YEAR * 2);
            LocalDate beginDate = getRandomDate();
            LocalDate endDate = beginDate.plusDays(randomDays);

            flatLeaseExperiences.add(FlatLeaseExperience
                    .builder()
                    .rentStart(Date.valueOf(beginDate))
                    .rentFinish(Date.valueOf(endDate))
                    .client(clients.remove(ThreadLocalRandom.current().nextInt(clients.size())))
                    .flat(flats.remove(ThreadLocalRandom.current().nextInt(flats.size())))
                    .build());
        }

        rentDBInitService.saveFlatLeaseExperiences(flatLeaseExperiences);
    }

    public void createRandomRentalInquiries(int countOfRecords) {
        List<Client> clients = rentDBInitService.findAllClients();
        List<Flat> flats = rentDBInitService.findAllFlats();
        List<RentalInquiries> rentalInquiries = new ArrayList<>();

        for (int i = 0; i < countOfRecords; i++) {
            rentalInquiries.add(RentalInquiries
                    .builder()
                    .client(clients.remove(ThreadLocalRandom.current().nextInt(clients.size())))
                    .flat(flats.remove(ThreadLocalRandom.current().nextInt(flats.size())))
                    .build());
        }

        rentDBInitService.saveRentalInquiries(rentalInquiries);
    }

    private LocalDate getRandomDate() {
        int year = LocalDate.now().getYear();
        int dayOfYear = ThreadLocalRandom.current().nextInt(1, 366);

        return LocalDate.ofYearDay(year, dayOfYear);
    }

    private static Info createRandomInfo() {
        return Info
                .builder()
                .firstName(firstNames.get(ThreadLocalRandom.current().nextInt(firstNames.size())))
                .lastName(lastNames.get(ThreadLocalRandom.current().nextInt(lastNames.size())))
                .numberPhone("NumberPhone" + ThreadLocalRandom.current().nextInt(99))
                .build();
    }
}