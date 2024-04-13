package com.company.homework_lesson_23.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Entity
@Builder
@Table(name = "flat")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"manager"})
public class Flat {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_room")
    private int numberCount;

    @Column(name = "area")
    private String area;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    Manager manager;

    @Override
    public String toString() {
        return " Id: " + id + "\n" +
                " Rooms number: " + numberCount + "\n" +
                " Area: " + area + "\n" +
                " Price: " + price + "$\n" +
                " Manager: " + manager.getInfo().getFirstName() +
                " " + manager.getInfo().getLastName() + " (" +
                manager.getInfo().getNumberPhone() + ")\n" +
                "=".repeat(15) + "\n";
    }
}
