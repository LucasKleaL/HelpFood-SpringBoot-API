package com.helpfood.donationservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(
		info = @Info(title = "HelpFood Donations Service API", version = "1.0.0", description = "HelpFood"),
		servers = {
				@Server(url = "http://localhost:8082"),
				@Server(url = "http://localhost:8082/donation"),
		}
)
@EnableFeignClients
@EnableRabbit
@SpringBootApplication
public class DonationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DonationServiceApplication.class, args);
	}

}
