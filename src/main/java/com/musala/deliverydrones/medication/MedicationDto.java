package com.musala.deliverydrones.medication;

import com.musala.deliverydrones.MessageConstants;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class MedicationDto {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = MessageConstants.ValidationMessages.INVALID_MEDICATION_NAME)
    private String name;

    @NotNull
    private Double weight;

    @NotBlank
    @Pattern(regexp = "^[A-Z0-9_]+$", message = MessageConstants.ValidationMessages.INVALID_MEDICATION_CODE)
    private String code;

    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
