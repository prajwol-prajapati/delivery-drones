package com.musala.deliverydrones.drone;

import com.musala.deliverydrones.medication.Medication;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
public class DroneDto {
    @NotBlank
    private String serialNumber;

    @NotNull
    private String model;

    private Double weightLimit;

    private int battery;

    private String state;

    private List<Medication> medications;
}
