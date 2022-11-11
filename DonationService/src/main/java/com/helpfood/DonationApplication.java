package com.helpfood;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(title = "API de doações", version = "1.0.0", description = "app helpfoo"),
		servers = {
				@Server(url = "http://localhost:8082"),
				@Server(url = "http://localhost:8082/helpfood-donation"),
		}
)
@SpringBootApplication
@EnableRabbit
public class DonationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DonationApplication.class, args);
	}

}
