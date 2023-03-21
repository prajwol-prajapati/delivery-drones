package com.musala.deliverydrones.drone;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class DroneResponseDto {
    private Long id;

    private String serialNumber;

    private String model;

    private Double weightLimit;

    private int battery;

    private String state;

    private Date createdAt;

    private Date updatedAt;
}
