package com.musala.deliverydrones.drone;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DroneMapper {
    Drone mapToDrone(DroneDto droneDt);
}
