package com.musala.deliverydrones.drone;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.musala.deliverydrones.AuditModel;
import com.musala.deliverydrones.medication.Medication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Drone extends AuditModel{
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique=true)
    private String serialNumber;

    @Column
    private Model model;

    @Column
    private Double weightLimit;

    @Column
    private int battery;

    @Column
    private State state;
    
    @OneToMany
    // @JoinColumn(name="drone_id")
    // @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Medication> medications;
}
