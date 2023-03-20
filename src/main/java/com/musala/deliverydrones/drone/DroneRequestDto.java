package com.musala.deliverydrones.drone;

import com.musala.deliverydrones.MessageConstants;
import com.musala.deliverydrones.medication.Medication;
import com.musala.deliverydrones.validation.ValueOfEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
public class DroneRequestDto {
    @NotBlank
    private String serialNumber;

    @NotBlank
    @ValueOfEnum(enumClass = Model.class, message = MessageConstants.ValidationMessages.MODEL_DOESNT_EXIST)
    private String model;

    @NotNull
    private Double weightLimit;

    @NotNull
    private int battery;

    @NotNull
    @ValueOfEnum(enumClass = State.class, message = MessageConstants.ValidationMessages.INVALID_STATE)
    private String state;

    private List<Medication> medications;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }
}
