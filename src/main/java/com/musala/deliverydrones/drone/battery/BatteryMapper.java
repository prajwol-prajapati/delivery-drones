package com.musala.deliverydrones.drone.battery;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BatteryMapper {
    Battery mapToBattery(BatteryDto batteryDto);
}
