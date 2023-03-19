package com.musala.deliverydrones.drone.battery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatteryService {
    @Autowired
    private BatteryRepository batteryRepository;

    @Autowired
    private BatteryMapper batteryMapper;

    public Battery save(BatteryDto batteryDto) {
        Battery battery = batteryMapper.mapToBattery(batteryDto);

        return batteryRepository.save(battery);
    }
}
