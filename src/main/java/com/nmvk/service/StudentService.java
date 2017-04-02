package com.nmvk.service;

import java.util.Scanner;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nmvk.dao.StudentDao;
import com.nmvk.domain.Student;

@Service
public class StudentService {
	
	@Autowired
	Scanner scanner;
	
	Student student = new Student();
	
	@Autowired
	StudentDao studentDao;

	/**
	 * 1. View/Edit Profile
	 * 2. View Courses/Enroll/Drop courses
	 * 3. View Pending courses (Pending, Rejected, Waitlisted)
	 * 4. View Grades
	 * 5. View/Pay Bill (Bill doesn't contain previous semester bills - just current semester bills) 
	 * 6. Logout
	 */
	public void mainMenu() {
		while (true) {
			student=studentDao.getStudentInfo();
			System.out.println("\n**********Main menu**********");
			System.out.println("1. View/Edit Profile ");
			System.out.println("2. View Courses/Enroll/Drop courses");
			System.out.println("3. View Pending courses (Pending, Rejected, Waitlisted) ");
			System.out.println("4. View Grades ");
			System.out.println("5. View/Pay Bill ");
			System.out.println("6. Logout");
			System.out.println("Please enter choice : ");

			String value = scanner.next();

			switch (value) {
			case "1":
				viewEditProfile();
				continue;
			case "2":
				viewCourses();
				continue;
			case "3":
				viewPendingCourses();
				continue;
			case "4":
				viewGrades();
				continue;
			case "5":
				viewPayBill();
				continue;
			case "6":
				break;
			default:
				System.out.println("Invalid option selected");
				continue;
			}			
			break;
		}
	}
	
	
	/**
	 * Press 0 to Go Back 1. First Name : ##### 2. Last Name : ##### 3. Email :
	 * ##### 4. Phone : #####  5. Level 6. Status
	 */
	private void viewEditProfile() {
		//TODO: get user's details and display after firstName lastName and other stuff 
		System.out.println("\n**********View/Edit Profile**********");
		System.out.println("Press 0 to Go Back");
		System.out.println("1. First Name  : "+ student.getFirstName());
		System.out.println("2. Last Name   : "+ student.getLastName());
		System.out.println("3. Email       : "+ student.getEmail());
		System.out.println("4. Phone : " + student.getPhone());
		if(student.isLevel()){
			System.out.println("Level : Graduate");
		}
		else{
			System.out.println("Level : UnderGraduate");
		}
		switch(student.getResidency()){
			case 1:
				System.out.println("Residency: In State");
				break;
			case 2:
				System.out.println("Residency: Out of State");
				break;
			case 3:
				System.out.println("Residency: International");
				break;
			default:
				System.out.println("Residency: International");			
		}
		
		System.out.println("Please enter choice of number 1/2/3/4 to edit that particular item : ");
		String value = scanner.next();

		while (!value.equals("0")) {
			switch (value) {
			case "1":
				System.out.println("Existing First Name  : "+ student.getFirstName());
				System.out.println("Enter New First Name : ");
				value = scanner.next();
				student.setFirstName(value);
				//TODO: DB stuff to update the FirstName
				break;
			case "2":
				System.out.println("Existing Last Name  : "+ student.getLastName());
				System.out.println("Enter New Last Name : ");
				value = scanner.next();
				student.setLastName(value);
				//TODO: DB stuff to update the LastName
				break;
			case "3":
				System.out.println("Existing Email  : "+ student.getEmail());
				System.out.println("Enter New Email (This is an unusual feature) : ");
				value = scanner.next();
				student.setEmail(value);
				//TODO: DB stuff to update the Email
				break;
			case "4":
				System.out.println("Existing Phone Number  : "+ student.getPhone());
				System.out.println("Enter New Phone Number : ");
				value = scanner.next();				
				student.setPhone(value);
				//TODO: DB stuff to update the Phone number
				break;
			default:
				System.out.println("Invalid option entered");
				System.out.println("Press \'0\' to Go Back OR Please enter choice of number 1/2/3/4 to edit that particular item : ");
				value = scanner.next();
				System.out.println(value.getClass().getSimpleName());
				//continue;			
			}
			System.out.println("***********Values Updated***********");
			break;
			//viewEditProfile();			
		}
	}

	
	/*View Available Courses
	Enroll:
	1. Enter Course Id : ####
	Press 0 To Go Back To Previous Menu
	View My Courses
	Show successfully added courses*/
	private void viewCourses(){
		
		System.out.println("Available courses: ");
		// TODO: List all the available courses
		
		System.out.println("Press 0 to Go Back");
		System.out.println("Enroll for a course : ");
		System.out.println("1. Enter Course Id : ");
		// TODO: handle case where use doesn't want to enroll
		
		String value = scanner.next();
		// TODO: Enroll user to a course as per input
		
		
		System.out.println("List of Enrolled Courses: ");
		// TODO: List all enrolled courses
		
		
		while (!value.equals("0")) {
			System.out.println("Only '0' is valid input.");
			System.out.println("Press 0 to Go Back");
			value = scanner.next();
		}
		
	}
	
	
	/*
		View Courses and Status
		Press 0 To Go Back To Previous Menu
	*/
	private void viewPendingCourses(){
		//TODO: not sure whether to show courses related to just student or all courses
		System.out.println("Courses and their Status");
		// TODO: Print list of courses which are pending/rejected/waitlisted
		System.out.println("Press 0 to Go Back");
		String value = scanner.next();
		while (!value.equals("0")) {
			System.out.println("Only '0' is valid input.");
			System.out.println("Press 0 to Go Back");
			value = scanner.next();
		}		
	}
	
	
	/*
	 View Letter Grades and GPA
	 * 1. Displays Letter Grades
	 * 2. Displays GPA
	 Press 0 To Go Back To Previous Menu
	*/
	private void viewGrades(){
		//TODO: Get grades of the student and display below
		System.out.println("Press 0 to Go Back");
		System.out.println("1. Letter Grades  : ");
		System.out.println("2. GPA : ");
	}
	
	
	/*
	 View Letter Bills
	 *	 1. Displays Student’s balance
	 * Pay Bills
	 *	 1. Enter amount
	 * Press 0 To Go Back To Previous Menu
	 */
	private void viewPayBill(){
		//TODO: Get grades of the student and display below. Handle pay bill stuff
		System.out.println("Press 0 to Go Back");
		System.out.println("View Letter Bills ");
		System.out.println("1. Display Student's balance  : ");
		System.out.println("Pay Bills ");
		System.out.println("2. Enter amount : ");
	}

}