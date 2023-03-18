package com.musala.deliverydrones.medication;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
