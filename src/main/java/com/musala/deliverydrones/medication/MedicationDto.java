package com.musala.deliverydrones.medication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicationDto {
    private String name;

    private Double weight;

    private String code;

    private String image;
}
