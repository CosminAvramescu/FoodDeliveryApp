package com.project.fooddeliveryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.log4j.PropertyConfigurator;

@SpringBootApplication
public class FoodDeliveryAppApplication {
	static {
		PropertyConfigurator.configure(FoodDeliveryAppApplication.class.getResource("/log4j.properties"));
	}

	public static void main(String[] args) {
		SpringApplication.run(FoodDeliveryAppApplication.class, args);
	}
}
