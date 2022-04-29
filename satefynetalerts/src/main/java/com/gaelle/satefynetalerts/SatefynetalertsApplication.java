package com.gaelle.satefynetalerts;

import com.gaelle.satefynetalerts.utils.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class SatefynetalertsApplication implements CommandLineRunner {
	@Autowired
	private Generator generator;

	public static void main(String[] args) {
		SpringApplication.run(SatefynetalertsApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		generator.generateData();
	}
}
