package com.musala.deliverydrones.drone;

import com.musala.deliverydrones.exception.BadRequestException;
import com.musala.deliverydrones.medication.Medication;
import com.musala.deliverydrones.medication.MedicationDto;
import com.musala.deliverydrones.medication.MedicationService;
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

    @Mock
    private MedicationService medicationService;

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
    @DisplayName("Should load valid medication")
    void loadMedication() {
        MedicationDto medicationDto = new MedicationDto();
        medicationDto.setName("medication-1");
        medicationDto.setWeight(200.00);
        medicationDto.setCode("CODE_2");
        medicationDto.setImage("data:image/jpg;base64,SUQsasasasas909090==");

        Medication medication = new Medication();
        medication.setName("medication-1");
        medication.setWeight(200.00);
        medication.setCode("CODE_2");
        medication.setImage("data:image/jpg;base64,SUQsasasasas909090==");

        Drone drone = new Drone();
        drone.setId(100L);
        drone.setBattery(30);
        drone.setModel(Model.CRUISER_WEIGHT);
        drone.setSerialNumber("SN-1");
        drone.setState(State.IDLE);
        drone.setWeightLimit(200.00);

        when(droneRepository.findById(drone.getId())).thenReturn(java.util.Optional.of(drone));
        when(medicationService.saveMedication(medicationDto)).thenReturn(medication);

        drone.setMedications(new ArrayList<>());
        drone.getMedications().add(medication);

        when(droneRepository.save(drone)).thenReturn(drone);

        Assertions.assertEquals(drone, sut.loadMedication(drone.getId(), medicationDto));
    }

    @Test
    @DisplayName("Should throw Bad request exception while loading medication in no idle drone")
    void shouldThrowExceptionWhileLoadingMedicationOnNonIdleDrone() {
        MedicationDto medicationDto = new MedicationDto();
        medicationDto.setName("medication-1");
        medicationDto.setWeight(200.00);
        medicationDto.setCode("CODE_2");
        medicationDto.setImage("data:image/jpg;base64,SUQsasasasas909090==");

        Medication medication = new Medication();
        medication.setName("medication-1");
        medication.setWeight(200.00);
        medication.setCode("CODE_2");
        medication.setImage("data:image/jpg;base64,SUQsasasasas909090==");

        Drone drone = new Drone();
        drone.setId(100L);
        drone.setBattery(30);
        drone.setModel(Model.CRUISER_WEIGHT);
        drone.setSerialNumber("SN-1");
        drone.setState(State.DELIVERED);
        drone.setWeightLimit(200.00);

        when(droneRepository.findById(drone.getId())).thenReturn(java.util.Optional.of(drone));

        assertThrows(BadRequestException.class, () -> sut.loadMedication(drone.getId(), medicationDto));
    }

    @Test
    @DisplayName("Should throw Bad request exception while loading medication weight greater than drone capacity")
    void shouldThrowExceptionWhileLoadingMedicationWeightIsGreaterThenDroneCapacity() {
        MedicationDto medicationDto = new MedicationDto();
        medicationDto.setName("medication-1");
        medicationDto.setWeight(210.00);
        medicationDto.setCode("CODE_2");
        medicationDto.setImage("data:image/jpg;base64,SUQsasasasas909090==");

        Medication medication = new Medication();
        medication.setName("medication-1");
        medication.setWeight(210.00);
        medication.setCode("CODE_2");
        medication.setImage("data:image/jpg;base64,SUQsasasasas909090==");

        Drone drone = new Drone();
        drone.setId(100L);
        drone.setBattery(30);
        drone.setModel(Model.CRUISER_WEIGHT);
        drone.setSerialNumber("SN-1");
        drone.setState(State.IDLE);
        drone.setWeightLimit(200.00);

        when(droneRepository.findById(drone.getId())).thenReturn(java.util.Optional.of(drone));

        assertThrows(BadRequestException.class, () -> sut.loadMedication(drone.getId(), medicationDto));
    }

    @Test
    @DisplayName("Should throw Bad request exception while loading medication in drone with 25% battery")
    void shouldThrowExceptionWhileLoadingMedicationWhenDroneBatteryLow() {
        MedicationDto medicationDto = new MedicationDto();
        medicationDto.setName("medication-1");
        medicationDto.setWeight(200.00);
        medicationDto.setCode("CODE_2");
        medicationDto.setImage("data:image/jpg;base64,SUQsasasasas909090==");

        Medication medication = new Medication();
        medication.setName("medication-1");
        medication.setWeight(200.00);
        medication.setCode("CODE_2");
        medication.setImage("data:image/jpg;base64,SUQsasasasas909090==");

        Drone drone = new Drone();
        drone.setId(100L);
        drone.setBattery(20);
        drone.setModel(Model.CRUISER_WEIGHT);
        drone.setSerialNumber("SN-1");
        drone.setState(State.IDLE);
        drone.setWeightLimit(200.00);

        when(droneRepository.findById(drone.getId())).thenReturn(java.util.Optional.of(drone));

        assertThrows(BadRequestException.class, () -> sut.loadMedication(drone.getId(), medicationDto));
    }

    @Test
    void getDroneMedication() {
        Medication medication = new Medication();
        medication.setName("medication-1");
        medication.setWeight(200.00);
        medication.setCode("CODE_2");
        medication.setImage("data:image/jpg;base64,SUQsasasasas909090==");

        Drone drone = new Drone();
        drone.setId(100L);
        drone.setBattery(40);
        drone.setModel(Model.CRUISER_WEIGHT);
        drone.setSerialNumber("SN-1");
        drone.setState(State.IDLE);
        drone.setWeightLimit(200.00);
        List<Medication> medications = new ArrayList<>();
        medications.add(medication);
        drone.setMedications(medications);

        when(droneRepository.findById(drone.getId())).thenReturn(java.util.Optional.of(drone));

        Assertions.assertEquals(medications, sut.getDroneMedication(drone.getId()));
    }

    @Test
    void getDrone() {
        Medication medication = new Medication();
        medication.setName("medication-1");
        medication.setWeight(200.00);
        medication.setCode("CODE_2");
        medication.setImage("data:image/jpg;base64,SUQsasasasas909090==");

        Drone drone = new Drone();
        drone.setId(100L);
        drone.setBattery(40);
        drone.setModel(Model.CRUISER_WEIGHT);
        drone.setSerialNumber("SN-1");
        drone.setState(State.IDLE);
        drone.setWeightLimit(200.00);
        List<Medication> medications = new ArrayList<>();
        medications.add(medication);
        drone.setMedications(medications);

        when(droneRepository.findById(drone.getId())).thenReturn(java.util.Optional.of(drone));

        Assertions.assertEquals(drone, sut.getDrone(drone.getId()));
    }

    @Test
    @DisplayName("Should throw bad request exception if incorrect drone id is entered while getting drone by id.")
    void shouldThrowErrorWhenIncorrectDroneIdWhileGettingDroneById() {
        Drone drone = new Drone();
        drone.setId(100L);
        drone.setBattery(40);
        drone.setModel(Model.CRUISER_WEIGHT);
        drone.setSerialNumber("SN-1");
        drone.setState(State.IDLE);
        drone.setWeightLimit(200.00);

        assertThrows(BadRequestException.class, () -> sut.getDrone(10L));
    }

    @Test
    void updateBatteryInfo() {
    }

    @Test
    void getAllActiveDrones() {
    }
}