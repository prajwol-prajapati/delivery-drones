package com.musala.deliverydrones.drone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musala.deliverydrones.ListResponse;
import com.musala.deliverydrones.medication.MedicationDto;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("drones")
public class DroneController {
    @Autowired
    private DroneService droneService;

    @PostMapping
    public Drone registerDrone(Drone drone) {
        return droneService.registerDrone(drone);
    }

    @GetMapping
    public ListResponse<Drone> getAllDrones() {
        return droneService.getAllDrones();
    }

    @PatchMapping("/{droneId}")
    public Drone loadMedication(@PathVariable Long droneId, @RequestBody MedicationDto medication) {
        return droneService.loadMedication(droneId, medication);
    }
}
