package com.musala.deliverydrones.medication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicationService {
    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private MedicationMapper medicationMapper;

    public Medication saveMedication(MedicationDto medicationRequest) {
        Medication medication = medicationMapper.mapToMedication(medicationRequest);

        return medicationRepository.save(medication);
    }

}
