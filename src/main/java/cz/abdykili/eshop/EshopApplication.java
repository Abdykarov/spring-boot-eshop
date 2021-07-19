package cz.abdykili.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class EshopApplication {

	public static final Logger logger = Logger.getLogger(EshopApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(EshopApplication.class, args);

	}

}
