package com.sina.cbs.information;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPI30
@OpenAPIDefinition(info = @Info(title = "Bank Global information service API", version = "1.0", description = ""))
public class GlobalInformationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlobalInformationServiceApplication.class, args);
	}

}
