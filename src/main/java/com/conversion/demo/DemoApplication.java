package com.conversion.demo;

import com.conversion.demo.model.ConversionModel;
import com.conversion.demo.service.ConversionService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(ConversionService conversionService) {
		return args -> {
			// read json and write to db

			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<ConversionModel>> typeReference = new TypeReference<List<ConversionModel>>() {
			};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/metrics.json");
			try {
				List<ConversionModel> Conversions = mapper.readValue(inputStream, typeReference);
				conversionService.save(Conversions);
				System.out.println("Metrics Saved!");
			} catch (IOException e) {
				System.out.println("Unable to save Metrics: " + e.getMessage());
			}
		};
	}
}
