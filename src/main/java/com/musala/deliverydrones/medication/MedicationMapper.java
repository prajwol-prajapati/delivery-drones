package com.musala.deliverydrones.medication;

import org.mapstruct.Mapper;

@Mapper
public interface MedicationMapper {
    Medication mapToMedication(MedicationDto medicationDto);
}
