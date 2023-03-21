package com.musala.deliverydrones.drone;

import com.musala.deliverydrones.medication.Medication;
import com.musala.deliverydrones.medication.MedicationDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DroneControllerTest {

    @Autowired
    private DroneController droneController;

    @Test
    void registerDrone() {
        DroneRequestDto droneRequest = new DroneRequestDto();
        droneRequest.setBattery(20);
        droneRequest.setModel(Model.CRUISER_WEIGHT.toString());
        droneRequest.setSerialNumber("SN-1");
        droneRequest.setState(State.DELIVERED.toString());
        droneRequest.setWeightLimit(200.00);

        DroneResponseDto drone = droneController.registerDrone(droneRequest);

        Assertions.assertEquals(20, drone.getBattery());
        Assertions.assertEquals(Model.CRUISER_WEIGHT.toString(), drone.getModel());
        Assertions.assertEquals("SN-1", drone.getSerialNumber());
        Assertions.assertEquals(State.DELIVERED.toString(), drone.getState());
        Assertions.assertEquals(200.00, drone.getWeightLimit());
    }


    @Test
    void loadMedication() {
        DroneRequestDto droneRequest = new DroneRequestDto();
        droneRequest.setBattery(50);
        droneRequest.setModel(Model.CRUISER_WEIGHT.toString());
        droneRequest.setSerialNumber("SN-MOCK");
        droneRequest.setState(State.IDLE.toString());
        droneRequest.setWeightLimit(200.00);

        DroneResponseDto mockDrone = droneController.registerDrone(droneRequest);

        MedicationDto medicationDto = new MedicationDto();
        medicationDto.setName("Med-1");
        medicationDto.setCode("CODE_1");
        medicationDto.setWeight(20.00);
        medicationDto.setImage("data:image/jpg;base64,SUQsasasasas909090==");

        Drone drone = droneController.loadMedication(mockDrone.getId(), medicationDto);
        Medication medication = drone.getMedications().stream().findFirst().get();

        Assertions.assertEquals(medication.getName(), medicationDto.getName());
        Assertions.assertEquals(medication.getCode(), medicationDto.getCode());
        Assertions.assertEquals(medication.getWeight(), medicationDto.getWeight());
        Assertions.assertEquals(medication.getImage(), medicationDto.getImage());
    }

    @Test
    void getAvailableDrones() {
    }

    @Test
    void getDroneMedication() {
    }

    @Test
    void getDrone() {
    }

    @Test
    void updateBatteryInfo() {
    }
}