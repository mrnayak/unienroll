package com.nmvk.service;

import java.util.List;
import java.util.Scanner;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nmvk.dao.CourseListingDao;
import com.nmvk.dao.FacultyDao;
import com.nmvk.dao.SemesterDao;
import com.nmvk.dao.StudentDao;
import com.nmvk.domain.CourseListing;
import com.nmvk.domain.Faculty;
import com.nmvk.domain.Semester;
import com.nmvk.domain.Student;

@Service
public class StudentService {
	
	@Autowired
	Scanner scanner;
	
	@Autowired
	FacultyDao facultyDao;
	
	Student student = new Student();
	
	@Autowired
	SemesterDao semesterDao;
	
	@Autowired
	CourseListingDao courseListingDao;
	
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
			System.out.println("2. View Courses");
			System.out.println("3. View Pending courses (Pending, Rejected, Waitlisted) ");
			System.out.println("4. View Grades ");
			System.out.println("5. View/Pay Bill ");
			System.out.println("6. Logout");
			System.out.println("Please enter choice : ");

			String value = scanner.next();

			switch (value) {
			case "1":
				viewEditProfile(true);
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
	private void viewEditProfile(boolean canEdit) {
		System.out.println("\n**********View/Edit Profile**********");
		System.out.println("Press 0 to Go Back");
		displayProfile();
		
		if(canEdit){
			System.out.println("Please enter choice of number 1/2/3/4 to edit that particular item : ");
			String value = scanner.next();

			while (!value.equals("0")) {
				switch (value) {
				case "1":
					System.out.println("Existing First Name  : "+ student.getFirstName());
					System.out.println("Enter New First Name : ");
					String firstName = scanner.next();
					while (firstName.trim().length() == 0) {
						System.out.println("Invalid First Name");
						System.out.println("Please enter again");
						firstName = scanner.next();
					}
					student.setFirstName(firstName);
					updateStudent(student);
					break;
				case "2":
					System.out.println("Existing Last Name  : "+ student.getLastName());
					System.out.println("Enter New Last Name : ");
					String lastName = scanner.next();
					while (lastName.trim().length() == 0) {
						System.out.println("Invalid Last Name");
						System.out.println("Please enter again");
						lastName = scanner.next();
					}
					student.setLastName(lastName);
					updateStudent(student);
					break;
				case "3":
					System.out.println("Existing DOB  : "+ student.getDateOfBirth());
					System.out.println("Enter New DOB (MM-DD-YYYY)  : ");
					String dob = scanner.next();
					student.setDateOfBirth(dob);
					updateStudent(student);
					break;
				case "4":
					System.out.println("Existing Phone Number  : "+ student.getPhone());
					System.out.println("Enter New Phone Number : ");
					String phone = scanner.next();				
					student.setPhone(phone);
					updateStudent(student);
					break;
				default:
					System.out.println("You entered value : "+ value + " which is");
					System.out.println("Invalid option entered");
					System.out.println("Press \'0\' to Go Back OR Please enter choice of number 1/2/3/4 to edit that particular item : ");
					value = scanner.next();
					//System.out.println(value.getClass().getSimpleName());
					//continue;
				}
				System.out.println("***********Values Updated***********");
				displayProfile();
				break;
							
			}
		}
	}


	private void displayProfile() {
		System.out.println("1. First Name  : "+ student.getFirstName());
		System.out.println("2. Last Name   : "+ student.getLastName());
		System.out.println("3. Date of Birth       : "+ student.getDateOfBirth());
		System.out.println("4. Phone : " + student.getPhone());
		System.out.println("Email       : "+ student.getEmail());//TODO: Add method to handle updating email address 
		System.out.println("Department : " + student.getAddress());
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
	}

	
	/*View Available Courses
	Enroll:
	1. Enter Course Id : ####
	Press 0 To Go Back To Previous Menu
	View My Courses
	Show successfully added courses*/
	private void viewCourses(){
		System.out.println("Open Semesters, choose: ");
		List<Semester> openSem = semesterDao.getActiveSem();
		System.out.println(openSem.toString());
		int counter = 1;
		for (Semester sem : openSem) {
			System.out.println(String.valueOf(counter)+":"+sem.getSem()+" "+sem.getYear());
			counter+=1;
		}
		String semResponse = scanner.next();
		Semester currentSem = openSem.get(Integer.valueOf(semResponse)-1);
		System.out.println(":"+currentSem.getSem()+" "+currentSem.getYear());
		
		System.out.println("Available courses: ");
		List<CourseListing> courseList=courseListingDao.getOfferingsBySem(currentSem.getSem(),currentSem.getYear());
		counter = 1;
		for (CourseListing courseEnt : courseList) {
			String schedule=courseEnt.isMon()?"M":"";
			schedule+=courseEnt.isTue()?"T":"";
			schedule+=courseEnt.isWed()?"W":"";
			schedule+=courseEnt.isThu()?"Th":"";
			schedule+=courseEnt.isFri()?"F":"";
			schedule+=String.valueOf(" "+courseEnt.getStart_hour())+":"+String.valueOf(courseEnt.getStart_min())+"-"+String.valueOf(courseEnt.getEnd_hour())+":"+String.valueOf(courseEnt.getEnd_min());
			
			List<Faculty> facultyList=facultyDao.getFacultyListForCourse(courseEnt.getCid(), courseEnt.getSched_id(), courseEnt.getClassroom_id());
			String facultytring="";
			for(Faculty faculty:facultyList){
				facultytring+=faculty.getName()+" ";
			}
			
			System.out.println(String.valueOf(counter)+":"+courseEnt.getName()+" "+courseEnt.getDepartment()+" "+courseEnt.getRemaining()+" "+schedule +" ");
			
			
			counter+=1;
		}
		
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
		Integer outstandingBill = student.getBill();
		System.out.println("Your balance  : " + outstandingBill);
		if (outstandingBill != null && outstandingBill > 0){
			System.out.println("Press 1 to Pay Bills or any key to go back : ");
			String value = scanner.next();
			while(value.equals("1")){
				System.out.println("Enter amount  : ");
				String amount = scanner.next();
				while (!amount.matches("[0-9]+")) {
					System.out.println("Invalid amount");
					System.out.println("Please enter amount again");
					amount = scanner.next();
				}
				Integer balance = outstandingBill - Integer.parseInt(amount);				
				student.setBill(balance);
				updateStudent(student);
				System.out.println("*********Payment of $ " + amount +" successful***********");
				System.out.println("Your balance  : " + balance);
				System.out.println("Exiting to main menu");
				value = "0";
			}
		}		
		
	}
	
	
	
	/* Note: DAO related helpers goes below here. 
	 If we are adding new method which uses DAO we can refer if we already have that method below */
	
	private void updateStudent(Student student){
		Integer level = student.isLevel() ? 1 : 0;
		try{
			studentDao.update(
					new Integer(student.getStudentID()), 
					student.getFirstName(), 
					student.getLastName(),
					student.getEmail(),
					student.getPhone(),
					student.getDateOfBirth(),
					level,
					new Integer(student.getResidency()), 
					student.getBill());	
		}
		catch(Exception e){
			System.out.println("***********SOME DB ERROR: FILED TO UPDATE STUDENT***********");
			System.out.println(e);
			System.out.println("***********SOME DB ERROR: FILED TO UPDATE STUDENT***********");
		}
		
	}
	
// TODO: Should Update email in Student table and AppUser table	
//	private void updateEmail(Student student){
//		try{
//			studentDao.updateEmail(new Integer(student.getStudentID()), student.getEmail());	
//		}
//		catch(Exception e){
//			System.out.println("***********SOME DB ERROR: FILED TO UPDATE STUDENT***********");
//			System.out.println(e);
//			System.out.println("***********SOME DB ERROR: FILED TO UPDATE STUDENT***********");
//		}
//		
//	}
}