package com.amcom.salesprocessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amcom.salesprocessor.service.MonitoringService;

@SpringBootApplication
public class TestApplication implements CommandLineRunner {
	
	@Autowired
	private MonitoringService monitoringService;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(TestApplication.class);
        app.run(args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		monitoringService.monitor();
    }

}
