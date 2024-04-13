package com.company.homework_lesson_23.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Entity
@Builder
@Table(name = "lease_experience")
@AllArgsConstructor
@NoArgsConstructor
public class FlatLeaseExperience {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rent_start")
    private Date rentStart;

    @Column(name = "rent_finish")
    private Date rentFinish;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "flat_id")
    private Flat flat;

    @Override
    public String toString() {
        return " Id: " + id + "\n" +
                " Start Rent Date: " + rentStart + "\n" +
                " Finish Rent Date: " + rentFinish + "\n" +
                " Client:\n" + client.getInfo().getFirstName() + " " +
                client.getInfo().getLastName() + "\n" +
                "-".repeat(10) + "\n" +
                " Flat:\n" +
                "  Area: " + flat.getArea() + "\n" +
                "  Rooms number: " + flat.getNumberCount() + "\n" +
                "=".repeat(15) + "\n";
    }
}