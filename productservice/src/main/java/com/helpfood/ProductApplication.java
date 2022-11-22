package com.helpfood;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(
		info = @Info(title = "HelpFood Products Service API", version = "1.0.0", description = "HelpFood"),
		servers = {
				@Server(url = "http://localhost:8081"),
				@Server(url = "http://localhost:8081/product"),
		}
)
@EnableFeignClients
@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

}
