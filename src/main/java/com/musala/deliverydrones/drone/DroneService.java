package com.musala.deliverydrones.drone;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musala.deliverydrones.medication.Medication;
import com.musala.deliverydrones.medication.MedicationDto;
import com.musala.deliverydrones.medication.MedicationService;

import javax.transaction.Transactional;


@Service
public class DroneService {
    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private MedicationService medicationService;

    @Autowired
    private DroneMapper droneMapper;

    public Drone registerDrone(DroneDto droneDto) {
        Drone drone = droneMapper.mapToDrone(droneDto);

        return droneRepository.save(drone);
    }

    public List<Drone> getAllDrones() {

        return droneRepository.findAll();
    }

    @Transactional
	public Drone loadMedication(Long droneId, MedicationDto medication) {
        Medication savedMedication = medicationService.saveMedication(medication);
        Drone drone = droneRepository.findById(droneId).orElseThrow(() -> new RuntimeException());
        // if(can load medication then add the medication)
        drone.getMedications().add(savedMedication);
        droneRepository.save(drone);
        // drone.setMedications(loadedMedications);

		return drone;
	}

}
