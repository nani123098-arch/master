package com.naniguides.springboot;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

@OpenAPIDefinition(

		info = @Info(title = "Nanaji Designed Application",
		             description = "My Description",
		version = "Robo 2.0",
				contact = @Contact(name = "Nanaji", email = "nanaibjhdg@gmail.com", url = "some.html"),
				license = @License(name = "Nani", url = "some.html")
		),
		externalDocs = @ExternalDocumentation ( description = "external", url = "svdhcd.ts"
		)

		)

public class SpringbootRestApiApplication {

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

	public static void main(String[] args) {

		SpringApplication.run(SpringbootRestApiApplication.class, args);
	}

}
