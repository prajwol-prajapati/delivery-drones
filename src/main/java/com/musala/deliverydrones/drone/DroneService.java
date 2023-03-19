package com.musala.deliverydrones.drone;

import com.musala.deliverydrones.MessageConstants;
import com.musala.deliverydrones.medication.Medication;
import com.musala.deliverydrones.medication.MedicationDto;
import com.musala.deliverydrones.medication.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class DroneService {
    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private MedicationService medicationService;

    @Autowired
    private DroneMapper droneMapper;

    private final int minLoadableBattery = 25;

    public Drone registerDrone(DroneDto droneDto) {
        Drone drone = droneMapper.mapToDrone(droneDto);

        return droneRepository.save(drone);
    }

    public List<Drone> getAvailableDrones() {
        return droneRepository.findAllByStateAndBatteryGreaterThan(State.IDLE, minLoadableBattery);
    }

    @Transactional
    public Drone loadMedication(Long droneId, MedicationDto medication) {
        Medication savedMedication = medicationService.saveMedication(medication);
        Drone drone = droneRepository.findById(droneId).orElseThrow(() -> new RuntimeException("Bad request"));

        validateMedicationLoad(drone, medication);

        drone.setState(State.LOADING);
        drone.getMedications().add(savedMedication);
        drone.setState(State.LOADED);
        droneRepository.save(drone);

        return drone;
    }

    private void validateMedicationLoad(Drone drone, MedicationDto medication) {
        if (!State.IDLE.equals(drone.getState())) {
            throw new RuntimeException(MessageConstants.ErrorMessages.INVALID_LOADING_STATE);
        }

        if (drone.getWeightLimit() < medication.getWeight()) {
            throw new RuntimeException(MessageConstants.ErrorMessages.WEIGHT_LIMIT_EXCEED);
        }
    }
}
