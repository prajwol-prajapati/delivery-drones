package com.musala.deliverydrones.drone;

import com.musala.deliverydrones.medication.Medication;
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

import com.musala.deliverydrones.medication.MedicationDto;

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
    public Drone registerDrone(@Valid @RequestBody DroneDto drone) {

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
    public Drone getAvailableDrones(@PathVariable Long droneId) {
        return droneService.getDrone(droneId);
    }
}
