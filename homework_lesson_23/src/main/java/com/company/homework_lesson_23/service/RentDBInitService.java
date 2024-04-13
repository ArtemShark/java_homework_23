package com.company.homework_lesson_23.service;

import com.company.homework_lesson_23.model.*;

import java.util.List;

public interface RentDBInitService {
    void deleteAllRowsInDB();

    List<Client> findAllClients();
    List<Manager> findAllManagers();
    List<Flat> findAllFlats();
    List<FlatLeaseExperience> findAllFlatLeaseExperiences();
    List<RentalInquiries> findAllRentalInquiries();

    void saveClients(List<Client> clients);
    void saveManagers(List<Manager> mangers);
    void saveFlats(List<Flat> flats);
    void saveFlatLeaseExperiences(List<FlatLeaseExperience> flatLeaseExperiences);
    void saveRentalInquiries(List<RentalInquiries> rentalInquiries);
}
