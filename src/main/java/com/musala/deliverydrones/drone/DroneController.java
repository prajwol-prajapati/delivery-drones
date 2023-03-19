package com.musala.deliverydrones.drone;

import com.musala.deliverydrones.drone.battery.Battery;
import com.musala.deliverydrones.drone.battery.BatteryDto;
import com.musala.deliverydrones.medication.Medication;
import com.musala.deliverydrones.medication.MedicationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("drones")
@Slf4j
@Validated
public class DroneController {
    @Autowired
    private DroneService droneService;

    @PostMapping
    public Drone registerDrone(@Valid @RequestBody DroneRequestDto drone) {

        return droneService.registerDrone(drone);
    }

    @PatchMapping("/{droneId}/load-medication")
    public Drone loadMedication(@PathVariable Long droneId, @RequestBody MedicationDto medication) {
        return droneService.loadMedication(droneId, medication);
    }

    @GetMapping
    public List<Drone> getAvailableDrones() {
        return droneService.getAvailableDrones();
    }

    @GetMapping("/{droneId}/medications")
    public List<Medication> getDroneMedication(@PathVariable Long droneId) {
        return droneService.getDroneMedication(droneId);
    }

    @GetMapping("/{droneId}")
    public Drone getDrone(@PathVariable Long droneId) {
        return droneService.getDrone(droneId);
    }

    @PatchMapping("/{droneId}/battery-info")
    public Battery updateBatteryInfo(@PathVariable Long droneId, @RequestBody BatteryDto batteryInfo) {
        return droneService.updateBatteryInfo(droneId, batteryInfo);
    }
}
