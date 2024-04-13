package com.company.homework_lesson_23.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@EqualsAndHashCode
public class FlatDTO {
    private int numberRoom;
    private String area;
    private BigDecimal price;
    private String managerFirstName;
    private String managerLastName;
    private Integer page = 0;
    private Integer pageSize = 5;
}
