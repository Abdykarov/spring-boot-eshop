package cz.abdykili.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.logging.Logger;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
@EnableEurekaClient
@EnableFeignClients
public class EshopApplication {
    public static void main(String[] args) {
        SpringApplication.run(EshopApplication.class, args);
    }

}
