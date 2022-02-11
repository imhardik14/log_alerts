package com.hardik4u.log_alerts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hardik4u.log_alerts.service.LogAlertsService;

@SpringBootApplication
public class LogAlertsApplication implements CommandLineRunner  {

	@Autowired
	LogAlertsService service;

	public static void main(String[] args) {
		SpringApplication.run(LogAlertsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		service.processFile();
		
	}

	
}
