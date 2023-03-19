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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Double getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(Double weightLimit) {
        this.weightLimit = weightLimit;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public List<Battery> getBatteryHistory() {
        return batteryHistory;
    }

    public void setBatteryHistory(List<Battery> batteryHistory) {
        this.batteryHistory = batteryHistory;
    }
}
