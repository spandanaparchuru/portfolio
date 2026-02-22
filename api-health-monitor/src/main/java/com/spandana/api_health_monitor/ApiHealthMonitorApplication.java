package com.spandana.api_health_monitor;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableScheduling
@SpringBootApplication
public class ApiHealthMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiHealthMonitorApplication.class, args);
	}

}
