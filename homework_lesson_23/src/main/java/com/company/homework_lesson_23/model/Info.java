package com.company.homework_lesson_23.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Info {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "number_phone")
    private String numberPhone;

    public static Info gettingInfo(Info curr, Info prev) {
        if (curr.getFirstName() != null && !curr.getFirstName().isEmpty()) {
            prev.setFirstName(curr.getFirstName());
        }
        if (curr.getLastName() != null && !curr.getLastName().isEmpty()) {
            prev.setLastName(curr.getLastName());
        }
        if (curr.getNumberPhone() != null && !curr.getNumberPhone().isEmpty()) {
            prev.setNumberPhone(curr.getNumberPhone());
        }

        return prev;
    }

    @Override
    public String toString() {
        return " Name: " + firstName + " " + lastName + "\n" +
                " Number Phone: " + numberPhone + "\n" +
                "=".repeat(15) + "\n";
    }
}
