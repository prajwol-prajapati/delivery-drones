package com.musala.deliverydrones.drone;

import com.musala.deliverydrones.exception.BadRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DroneServiceTest {
    @InjectMocks
    private DroneService sut;

    @Mock
    private DroneMapper droneMapper;

    @Mock
    private DroneRepository droneRepository;

    @Test
    @DisplayName("Should Successfully Save a drone")
    void registerDrone() {
        DroneRequestDto droneRequest = new DroneRequestDto();
        droneRequest.setBattery(30);
        droneRequest.setModel(Model.CRUISER_WEIGHT.toString());
        droneRequest.setSerialNumber("SN-1");
        droneRequest.setState(State.DELIVERED.toString());
        droneRequest.setWeightLimit(200.00);

        Drone drone = new Drone();
        drone.setBattery(30);
        drone.setModel(Model.CRUISER_WEIGHT);
        drone.setSerialNumber("SN-1");
        drone.setState(State.DELIVERED);
        drone.setWeightLimit(200.00);

        DroneResponseDto droneResponse = new DroneResponseDto();
        droneResponse.setBattery(30);
        droneResponse.setModel(Model.CRUISER_WEIGHT.toString());
        droneResponse.setSerialNumber("SN-1");
        droneResponse.setState(State.DELIVERED.toString());
        droneResponse.setWeightLimit(200.00);

        when(droneMapper.mapToDrone(any())).thenReturn(drone);
        when(droneRepository.save(any())).thenReturn(drone);
        when(droneMapper.mapToDroneResponse((Drone) any())).thenReturn(droneResponse);

        DroneResponseDto sutDrone = sut.registerDrone(droneRequest);

        Assertions.assertEquals(droneResponse.getBattery(), sutDrone.getBattery());
        Assertions.assertEquals(droneResponse.getModel(), sutDrone.getModel());
        Assertions.assertEquals(droneResponse.getSerialNumber(), sutDrone.getSerialNumber());
        Assertions.assertEquals(droneResponse.getState(), sutDrone.getState());
        Assertions.assertEquals(droneResponse.getWeightLimit(), sutDrone.getWeightLimit());
    }

    @Test
    @DisplayName("Should throw Bad request exception error if battery percentage is beyond the range of 0-100")
    void successfulDroneRegister() {
        DroneRequestDto droneRequest = new DroneRequestDto();
        droneRequest.setBattery(-30);
        droneRequest.setModel(Model.CRUISER_WEIGHT.toString());
        droneRequest.setSerialNumber("SN-1");
        droneRequest.setState(State.DELIVERED.toString());
        droneRequest.setWeightLimit(200.00);

        assertThrows(BadRequestException.class, () -> sut.registerDrone(droneRequest));
    }

    @Test
    @DisplayName("Should get available drones for loading.")
    void getAvailableDrones() {
        List<Drone> drones = new ArrayList<>();
        Drone drone = new Drone();
        drone.setBattery(30);
        drone.setModel(Model.CRUISER_WEIGHT);
        drone.setSerialNumber("SN-1");
        drone.setState(State.DELIVERED);
        drone.setWeightLimit(200.00);

        drones.add(drone);

        List<DroneResponseDto> dronesResponse = new ArrayList<>();
        DroneResponseDto droneResponse = new DroneResponseDto();
        droneResponse.setBattery(30);
        droneResponse.setModel(Model.CRUISER_WEIGHT.toString());
        droneResponse.setSerialNumber("SN-1");
        droneResponse.setState(State.DELIVERED.toString());
        droneResponse.setWeightLimit(200.00);

        dronesResponse.add(droneResponse);

        when(droneRepository.findAllByStateAndBatteryGreaterThan(State.IDLE, 25)).thenReturn(drones);
        when(droneMapper.mapToDronesResponse(drones)).thenReturn(dronesResponse);

        Assertions.assertEquals(dronesResponse, sut.getAvailableDrones());
    }

    @Test
    @DisplayName("Should return empty array if no drones available.")
    void shouldReturnEmptyArrayIfNoDronesAvailable() {
        List<Drone> drones = new ArrayList<>();

        List<DroneResponseDto> dronesResponse = new ArrayList<>();

        when(droneRepository.findAllByStateAndBatteryGreaterThan(State.IDLE, 25)).thenReturn(drones);
        when(droneMapper.mapToDronesResponse(drones)).thenReturn(dronesResponse);

        Assertions.assertEquals(dronesResponse, sut.getAvailableDrones());
    }

    @Test
    void loadMedication() {

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

    @Test
    void getAllActiveDrones() {
    }
}