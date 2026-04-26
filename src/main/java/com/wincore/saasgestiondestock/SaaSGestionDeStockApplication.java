package com.wincore.saasgestiondestock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SaaSGestionDeStockApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaaSGestionDeStockApplication.class, args);
    }

}
