package com.musala.deliverydrones.medication;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Long>{
    
}
