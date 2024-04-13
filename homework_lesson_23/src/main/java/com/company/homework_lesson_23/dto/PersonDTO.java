package com.company.homework_lesson_23.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class PersonDTO {
    private String firstName;
    private String lastName;
    private String numberPhone;
}