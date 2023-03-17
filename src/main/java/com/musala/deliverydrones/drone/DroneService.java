package com.musala.deliverydrones.drone;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.musala.deliverydrones.ListResponse;
import com.musala.deliverydrones.exception.BadRequestException;
import com.musala.deliverydrones.medication.Medication;
import com.musala.deliverydrones.medication.MedicationDto;
import com.musala.deliverydrones.medication.MedicationService;

import jakarta.transaction.Transactional;

@Service
public class DroneService {
    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private MedicationService medicationService;

    public Drone registerDrone(Drone drone) {
        
        return droneRepository.save(drone);
    }

    public ListResponse<Drone> getAllDrones() {
        ListResponse response = new ListResponse<Drone>();
        response.setResults(droneRepository.findAll());

        return response;
    }

    @Transactional
	public Drone loadMedication(Long droneId, MedicationDto medication) {
        Medication savedMedication = medicationService.saveMedication(medication);
        Drone drone = droneRepository.findById(droneId).orElseThrow(() -> new BadRequestException());
        // if(can load medication then add the medication)
        drone.getMedications().add(savedMedication);
        droneRepository.save(drone);
        // drone.setMedications(loadedMedications);
        
		return drone;
	}
    
}
