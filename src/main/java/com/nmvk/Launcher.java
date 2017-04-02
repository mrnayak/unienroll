package com.nmvk;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nmvk.service.LoginService;


/**
 * Entry point for the application. Nothing important here.
 * 
 * @author Raghav
 */
@EnableAutoConfiguration
@SpringBootApplication
public class Launcher implements CommandLineRunner{
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	Scanner scanner;
	
	public static void main(String[] args) {
		System.out.println("Please wait.... this may take upto 30 seconds");
		SpringApplication.run(Launcher.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		System.out.println("Welcome to UniEnroll");
		System.out.println("Please enter menu number to select");
		System.out.println("1. Login");
		System.out.println("2. Exit");
		
		String value = scanner.next();
		
		if(value.equals("1")) {
			loginService.login();
		}
	}
}
