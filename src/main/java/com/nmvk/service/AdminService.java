package com.nmvk.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.nmvk.dao.CourseDao;

import com.nmvk.dao.StudentDao;
import com.nmvk.domain.Student;


@Service
public class AdminService {

	@Autowired
	Scanner scanner;
	
	@Autowired
	CourseDao courseDao;
	


	@Autowired
	LoginService loginService;

	@Autowired
	StudentDao studentDao;

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
			case "2" :
				enrollStudent();
				continue;
			case "3" :
				viewDetails();
				continue;
			case "4":
				addCourse();
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
	private void addCourse() {
		System.out.println("1. Course Name");
		String name = scanner.next();
		System.out.println("2. Department: ");
		String department = scanner.next();
		System.out.println("3. Credits: ");
		Integer credits = Integer.parseInt(scanner.next());
		System.out.println("4. Course Level: ");
		Integer courseLevel = Integer.parseInt(scanner.next());
		System.out.println("5. Course ID: ");
		Integer cId = Integer.parseInt(scanner.next());
		//System.out.println(cId + cName+cDepartment+Credits+cLevel);
		courseDao.insert(cId, name, department, credits, courseLevel);
		

	}
	
	
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

	private void enrollStudent() {
		System.out.println("Enroll a new student");

		System.out.println("1. Enter Student Id : ");
		String studentId = scanner.next();

		while (!studentId.matches("[0-9]+")) {
			System.out.println("Invalid Student ID, ID should be numeric");
			System.out.println("Please enter Student ID again");
			studentId = scanner.next();
		}

		System.out.println("2. Enter Student’s First Name: ");
		String firstName = scanner.next();

		while (firstName.trim().length() == 0) {
			System.out.println("Invalid First Name");
			System.out.println("Please enter again");
			firstName = scanner.next();
		}

		System.out.println("3. Enter Student’s Last Name:");
		String lastName = scanner.next();

		while (lastName.trim().length() == 0) {
			System.out.println("Invalid Last Name");
			System.out.println("Please enter again");
			lastName = scanner.next();
		}

		System.out.println("4. Enter Student’s D.O.B(MM-DD-YYYY): ");
		String dob = scanner.next();

		System.out.println("5. Enter Student’s Level:");
		System.out.println("Enter 0 for UG, 1 for PG");

		String level = scanner.next();

		while (!level.matches("[0-1]")) {
			System.out.println("Invalid Level");
			System.out.println("Please enter level again");
			level = scanner.next();
		}
		System.out.println("6. Enter Student’s Residency Status: ");
		System.out.println("Enter 0 for Resident, 1 for Non Resident, 2 for International");

		String resident = scanner.next();

		while (!resident.matches("[0-2]")) {
			System.out.println("Invalid residency");
			System.out.println("Please enter residency again");
			resident = scanner.next();
		}

		System.out.println("7. Enter Amount Owed(if any):");
		String amount = scanner.next();

		while (!amount.matches("[0-9]+")) {
			System.out.println("Invalid amount");
			System.out.println("Please enter amount again");
			amount = scanner.next();
		}

		studentDao.insert(Integer.parseInt(studentId), firstName, lastName, dob, Integer.parseInt(level),
				Integer.parseInt(resident), Integer.parseInt(amount));
	}
	

	
	void viewDetails() {
		System.out.println("Please Enter Student Id: ");
		String id = scanner.next();
		
		while(!id.matches("[0-9]+")) {
			System.out.println("Invalid Student Id");
			System.out.println("Please enter student id");
			id = scanner.next();
		}
		
		Student student = studentDao.getById(Integer.parseInt(id));
		
		if(student == null) {
			System.out.println("Student not found in database");
			return;
		}
		
		System.out.println("1. First Name :" + student.getFirstName());
		System.out.println("2. Last Name :" + student.getLastName());
		System.out.println("3. D.O.B(MM-DD-YYYY) :" + student.getDateOfBirth());
		System.out.println("4. Student’s Level :" + student.isLevel());
		System.out.println("5. Student’s Residency Status :" +student.getResidency());
		System.out.println("6. Amount Owed(if any) :" + student.getBill());
		System.out.println("7. GPA :" + student.getBill());
		System.out.println("8. Phone :" + student.getPhone());
		System.out.println("9. Email :" + student.getEmail());
		System.out.println("10. Address :" + student.getAddress());
		System.out.println("Press 0 To Go Back To Previous Menu");
		String value = scanner.next();
	}

}
