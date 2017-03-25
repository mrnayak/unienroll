package com.nmvk;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Entry point for the application. Nothing important here.
 * 
 * @author Raghav
 */
@EnableAutoConfiguration
@SpringBootApplication
public class Launcher implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Launcher.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
	}
}
