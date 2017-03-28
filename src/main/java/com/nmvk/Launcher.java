package com.nmvk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nmvk.service.AdminService;


/**
 * Entry point for the application. Nothing important here.
 * 
 * @author Raghav
 */
@EnableAutoConfiguration
@SpringBootApplication
public class Launcher implements CommandLineRunner{
	
	@Autowired
	AdminService adminService;
	
	public static void main(String[] args) {
		System.out.println("Please wait.... this may take upto 30 seconds");
		SpringApplication.run(Launcher.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		adminService.mainMenu();
	}
}
