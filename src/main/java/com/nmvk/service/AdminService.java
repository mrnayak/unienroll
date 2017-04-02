package com.nmvk.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

	@Autowired
	Scanner scanner;

	/**
	 * 1. View Profile 2. Enroll A New Student 3. View Student’s Details 4.
	 * View/Add Courses 5. View/Add Course Offering 6. View/Approve Special
	 * Enrollment Requests 7. Enforce Add/Drop Deadline 8. Logout
	 */
	public void mainMenu() {
		while (true) {
			System.out.println("1. View Profile ");
			System.out.println("2. Enroll A New Student");
			System.out.println("3. View Student’s Details ");
			System.out.println("4. View/Add Courses ");
			System.out.println("5. View/Add Course Offering ");
			System.out.println("6. View/Approve Special Enrollment Requests ");
			System.out.println("7. Enforce Add/Drop Deadline ");
			System.out.println("8. Logout");
			System.out.println("Please enter choice : ");

			String value = scanner.next();

			switch (value) {
			case "1":
				viewProfile();
				continue;
			case "8":
				break;
			default:
				System.out.println("Invalid option selected");
				continue;
			}
			
			break;
		}
	}

	/**
	 * Press 0 to Go Back 1. First Name : ##### 2. Last Name : ##### 3. D.O.B :
	 * ##### 4. Employee Id : #####
	 */
	private void viewProfile() {
		System.out.println("Press 0 to Go Back");
		System.out.println("1. First Name  : ");
		System.out.println("2. Last Name   : ");
		System.out.println("3. D.O.B       : ");
		System.out.println("4. Employee Id : ");

		String value = scanner.next();

		while (!value.equals("0")) {
			System.out.println("Only '0' is valid input.");
			System.out.println("Press 0 to Go Back");
			value = scanner.next();
		}

	}
	
	
}
