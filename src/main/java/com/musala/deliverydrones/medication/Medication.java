package com.musala.deliverydrones.medication;

import javax.persistence.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Medication {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Double weight;

    @Column
    private String code;

    @Column
    private String image;
}
