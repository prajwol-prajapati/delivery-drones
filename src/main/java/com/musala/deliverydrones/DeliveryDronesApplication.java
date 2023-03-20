package com.musala.deliverydrones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EntityScan
@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
public class DeliveryDronesApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryDronesApplication.class, args);
    }

}
