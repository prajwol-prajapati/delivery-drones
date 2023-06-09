package com.musala.deliverydrones.drone;

import com.musala.deliverydrones.MessageConstants;
import com.musala.deliverydrones.drone.battery.Battery;
import com.musala.deliverydrones.drone.battery.BatteryDto;
import com.musala.deliverydrones.drone.battery.BatteryService;
import com.musala.deliverydrones.exception.BadRequestException;
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

    @Autowired
    private BatteryService batteryService;

    private final int minLoadableBattery = 25;

    private final int minBatteryPercentage = 0;

    private final int maxBatteryPercentage = 100;

    public DroneResponseDto registerDrone(DroneRequestDto droneRequestDto) {
        if (droneRequestDto.getBattery() > maxBatteryPercentage || droneRequestDto.getBattery() < minBatteryPercentage) {
            throw new BadRequestException(MessageConstants.ErrorMessages.INVALID_BATTERY_PERCENTAGE);
        }
        Drone drone = droneMapper.mapToDrone(droneRequestDto);
        Drone savedDrone = droneRepository.save(drone);

        return droneMapper.mapToDroneResponse(savedDrone);
    }

    public List<DroneResponseDto> getAvailableDrones() {
        List<Drone> availableDrones = droneRepository.findAllByStateAndBatteryGreaterThan(State.IDLE, minLoadableBattery);

        return droneMapper.mapToDronesResponse(availableDrones);
    }

    @Transactional
    public Drone loadMedication(Long droneId, MedicationDto medication) {
        Drone drone = droneRepository.findById(droneId).orElseThrow(() -> new BadRequestException(MessageConstants.ErrorMessages.DRONE_DOES_NOT_EXIST));
        Medication savedMedication = medicationService.saveMedication(medication);

        validateMedicationLoad(drone, medication);

        drone.setState(State.LOADING);
        drone.getMedications().add(savedMedication);
        drone.setState(State.LOADED);
        droneRepository.save(drone);

        return drone;
    }

    private void validateMedicationLoad(Drone drone, MedicationDto medication) {
        if (!State.IDLE.equals(drone.getState())) {
            throw new BadRequestException(MessageConstants.ErrorMessages.INVALID_LOADING_STATE);
        }

        if (drone.getWeightLimit() < medication.getWeight()) {
            throw new BadRequestException(MessageConstants.ErrorMessages.WEIGHT_LIMIT_EXCEED);
        }

        if (drone.getBattery() < minLoadableBattery) {
            throw new BadRequestException(MessageConstants.ErrorMessages.BATTERY_LOW_FOR_LOADING);
        }
    }

    public List<Medication> getDroneMedication(Long droneId) {
        Drone drone = droneRepository.findById(droneId).orElseThrow(() -> new BadRequestException(MessageConstants.ErrorMessages.DRONE_DOES_NOT_EXIST));

        return drone.getMedications();
    }

    public Drone getDrone(Long droneId) {
        Drone drone = droneRepository.findById(droneId).orElseThrow(() -> new BadRequestException(MessageConstants.ErrorMessages.DRONE_DOES_NOT_EXIST));

        return drone;
    }

    @Transactional
    public Battery updateBatteryInfo(Long droneId, BatteryDto batteryInfo) {
        if (batteryInfo.getPercentage() > maxBatteryPercentage || batteryInfo.getPercentage() < minBatteryPercentage) {
            throw new BadRequestException(MessageConstants.ErrorMessages.INVALID_BATTERY_PERCENTAGE);
        }
        Drone drone = droneRepository.findById(droneId).orElseThrow(() -> new BadRequestException(MessageConstants.ErrorMessages.DRONE_DOES_NOT_EXIST));
        Battery savedBatteryInfo = batteryService.save(batteryInfo);

        drone.setBattery(batteryInfo.getPercentage());
        drone.getBatteryHistory().add(savedBatteryInfo);
        droneRepository.save(drone);

        return savedBatteryInfo;
    }

    public List<Drone> getAllActiveDrones() {
        return droneRepository.findAllByBatteryGreaterThan(minBatteryPercentage);
    }
}
