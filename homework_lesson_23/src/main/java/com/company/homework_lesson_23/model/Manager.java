package com.company.homework_lesson_23.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Builder
@Table(name = "managers")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"flat"})
public class Manager {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Info info;

    @JsonIgnore
    @OneToMany(mappedBy = "manager")
    private List<Flat> flats;

    @Override
    public String toString() {
        return " Id: " + id + "\n" + info;
    }
}
