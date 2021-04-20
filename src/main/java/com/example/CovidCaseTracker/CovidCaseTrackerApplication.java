package com.example.CovidCaseTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CovidCaseTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovidCaseTrackerApplication.class, args);
	}

}
