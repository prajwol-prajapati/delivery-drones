package com.musala.deliverydrones.medication;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MedicationServiceTest {
    @InjectMocks
    private MedicationService sut   ;

    @Mock
    private MedicationRepository medicationRepository;

    @Mock
    private MedicationMapper medicationMapper;

    @Test
    @DisplayName("Should save medication.")
    void saveMedication() {
        MedicationDto medicationDto = new MedicationDto();
        medicationDto.setName("medication-1");
        medicationDto.setWeight(200.00);
        medicationDto.setCode("CODE_2");
        medicationDto.setImage("data:image/jpg;base64,SUQsasasasas909090==");

        Medication medication = new Medication();
        medication.setName("medication-1");
        medication.setWeight(200.00);
        medication.setCode("CODE_2");
        medication.setImage("data:image/jpg;base64,SUQsasasasas909090==");

        when(medicationMapper.mapToMedication(medicationDto)).thenReturn(medication);
        when(medicationRepository.save(medication)).thenReturn(medication);

        Assertions.assertEquals(medication, sut.saveMedication(medicationDto));
    }
}