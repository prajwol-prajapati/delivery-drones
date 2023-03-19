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
        DroneRequestDto drone = new DroneRequestDto();
        drone.setBattery(20);
        drone.setModel("CRUISER_WEIGHT");
        drone.setSerialNumber("dhjjj1");
        drone.setState(State.DELIVERED.toString());
        drone.setWeightLimit(200.00);

        Drone drone1 = droneController.registerDrone(drone);

        Assertions.assertNotNull(drone1.getId());
        Assertions.assertEquals(20, drone1.getBattery());

    }
}