package com.musala.deliverydrones.drone;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DroneMapper {
    Drone mapToDrone(DroneRequestDto droneRequestDto);

    DroneResponseDto mapToDroneResponse(DroneRequestDto droneRequestDto);

    DroneResponseDto mapToDroneResponse(Drone drone);

    List<DroneResponseDto> mapToDronesResponse(List<Drone> drones);
}
