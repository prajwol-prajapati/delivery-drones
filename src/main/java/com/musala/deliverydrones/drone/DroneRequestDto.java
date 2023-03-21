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
}
