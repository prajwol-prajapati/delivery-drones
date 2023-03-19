package com.musala.deliverydrones.medication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicationDto {
    private String name;

    private Double weight;

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
