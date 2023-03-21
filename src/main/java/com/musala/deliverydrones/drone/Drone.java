package com.musala.deliverydrones.drone;

import com.musala.deliverydrones.AuditModel;
import com.musala.deliverydrones.drone.battery.Battery;
import com.musala.deliverydrones.medication.Medication;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class Drone extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
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
    @JoinColumn(name = "drone_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Medication> medications;

    @OneToMany
    @JoinColumn(name = "drone_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Battery> batteryHistory;
}
