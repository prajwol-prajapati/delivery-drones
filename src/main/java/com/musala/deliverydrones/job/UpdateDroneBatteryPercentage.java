package com.musala.deliverydrones.job;

import com.musala.deliverydrones.drone.Drone;
import com.musala.deliverydrones.drone.DroneService;
import com.musala.deliverydrones.drone.battery.BatteryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class UpdateDroneBatteryPercentage {
    private static final long DRONE_BATTERY_UPDATE_MILL_SEC = 60000;

    @Autowired
    private DroneService droneService;

    @Scheduled(fixedDelay = DRONE_BATTERY_UPDATE_MILL_SEC)
    public void updateBatteryInfo() {
//        log.info("Drone battery auto decrement by 1");
        List<Drone> drones = droneService.getAllActiveDrones();

        drones.forEach(drone -> {
            BatteryDto batteryDto = new BatteryDto();
            int updatedBatteryPercentage = drone.getBattery() - 1;
            batteryDto.setPercentage(updatedBatteryPercentage);
            droneService.updateBatteryInfo(drone.getId(), batteryDto);
        });
    }
}
