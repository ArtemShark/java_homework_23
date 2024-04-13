package com.company.homework_lesson_23.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "rental_inquiries")
@AllArgsConstructor
@NoArgsConstructor
public class RentalInquiries {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "flat_id", nullable = false)
    private Flat flat;

    @Override
    public String toString() {
        return " Id: " + id + "\n" +
                " Client: " + client.getInfo().getFirstName() + " " +
                client.getInfo().getLastName() + "\n" +
                " Flat:\n" +
                "  Area: " + flat.getArea() + "\n" +
                "  Flat owner: " + flat.getManager().getInfo().getFirstName() +
                flat.getManager().getInfo().getLastName() +
                " (" + flat.getManager().getInfo().getNumberPhone() + ")\n" +
                "=".repeat(15) + "\n";
    }
}

