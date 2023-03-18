package com.musala.deliverydrones.medication;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MedicationMapper {
    Medication mapToMedication(MedicationDto medicationDt);
}
