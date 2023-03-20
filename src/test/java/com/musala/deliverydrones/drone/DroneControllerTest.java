package com.musala.deliverydrones.drone;

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


}