package com.hardik4u.log_alerts;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogAlertsApplication implements CommandLineRunner {

	
	public static void main(String[] args) {
		
		for(String arg:args)
			System.out.println(arg);
		
		SpringApplication.run(LogAlertsApplication.class, args);
		
		
	}

	@Override
	public void run(String... args) throws Exception {
		Thread.currentThread().join();
	}

	
}
