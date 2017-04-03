package com.nmvk.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nmvk.dao.AdministratorDao;
import com.nmvk.dao.CourseDao;
import com.nmvk.dao.CourseListingDao;
import com.nmvk.dao.EnrollmentDao;
import com.nmvk.dao.FacultyDao;
import com.nmvk.dao.FacultyGroupDao;
import com.nmvk.dao.OfferingDao;
import com.nmvk.dao.ScheduleDao;
import com.nmvk.dao.SemesterDao;
import com.nmvk.dao.SpecialPermDao;
import com.nmvk.dao.StudentDao;
import com.nmvk.domain.Administrator;
import com.nmvk.domain.Course;
import com.nmvk.domain.CourseListing;
import com.nmvk.domain.Enrollments;
import com.nmvk.domain.Faculty;
import com.nmvk.domain.Offering;
import com.nmvk.domain.Semester;
import com.nmvk.domain.SpecialReq;
import com.nmvk.domain.Student;

@Service
public class AdminService {

	@Autowired
	Scanner scanner;

	@Autowired
	CourseDao courseDao;

	@Autowired
	SpecialPermDao specialPermDao;

	@Autowired
	LoginService loginService;

	@Autowired
	SemesterDao semesterDao;
	@Autowired
	StudentDao studentDao;

	@Autowired
	FacultyDao facultyDao;

	@Autowired
	ScheduleDao scheduleDao;

	@Autowired
	EnrollmentDao enrollmentDao;

	@Autowired
	OfferingDao offeringDao;

	@Autowired
	FacultyGroupDao facultyGroupDao;

	Administrator administrator;

	@Autowired
	AdministratorDao administratorDao;

	@Autowired
	CourseListingDao courseListingDao;

	/**
	 * 1. View Profile 2. Enroll A New Student 3. View Student’s Details 4.
	 * View/Add Courses 5. View/Add Course Offering 6. View/Approve Special
	 * Enrollment Requests 7. Enforce Add/Drop Deadline 8. Logout
	 */
	public void mainMenu() {
		administrator = administratorDao.getByEmail(loginService.appUser.getUserName());

		while (true) {
			System.out.println("1. View Profile "); // done
			System.out.println("2. Enroll A New Student");
			System.out.println("3. View Student’s Details "); // done
			System.out.println("4. View/Add/Edit Courses ");// done
			System.out.println("5. View/Add Course Offering "); // Add done
			System.out.println("6. View/Approve Special Enrollment Requests ");// almost
			System.out.println("7. Enforce Add/Drop Deadline ");// done
			System.out.println("8. Logout");// done
			System.out.println("Please enter choice : ");

			String value = scanner.next();

			switch (value) {
			case "1":
				viewProfile();
				continue;
			case "2":
				enrollStudent();
				continue;
			case "3":
				viewDetails();
				continue;
			case "5":
				viewAddCourse();
				continue;
			case "4":
				addCourse();
				continue;
			case "6":
				specialReqs();
				continue;
			case "7":
				setActiveSems();
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

	private void setActiveSems() {
		while (true) {
			System.out.println("Enter 0 to keep unchecking semesters, else press 1");
			Integer choice = Integer.parseInt(scanner.next());
			if (choice == 1) {
				return;
			} else if (choice == 0) {
				System.out.println("Here is a list of all active semesters");
				List<Semester> openSem = semesterDao.getActiveSem();
				System.out.println(openSem.toString());
				for (int i = 0; i < openSem.size(); i++) {
					System.out.println(i + "Semester: " + openSem.get(i).getKey().getSem() + " Year: "
							+ openSem.get(i).getKey().getYear());
				}
				System.out.println("Enter the semester to close");
				String sem = scanner.next();
				System.out.println("Enter the Year of the Semester to Close");
				String year = scanner.next();
				semesterDao.closeSem(sem, year);
				System.out.println(sem + ", " + year + " has been closed.");

			}
		}
	}

	private void addCourse() {
		System.out.println("To view a course: 0, to Create anew one: 1, to Edit: 2");
		Integer choice = Integer.parseInt(scanner.next());
		if (choice == 0) {
			System.out.println("Enter course number to search");
			Integer cID = Integer.parseInt(scanner.next());
			Course c = new Course();
			c = courseDao.getById(cID);
			System.out.println("The details are :-");
			System.out.println("Course ID:" + c.getcId());
			System.out.println("Name: " + c.getName());
			System.out.println("Department: " + c.getDepartment());
			System.out.println("Credit: " + c.getCredit());
			System.out.println("Level" + c.getCourseLevel());

		} else if (choice == 1) {
			System.out.println("1. Course ID: ");
			Integer cId = Integer.parseInt(scanner.next());
			System.out.println("2. Course Name");
			String name = scanner.next();
			System.out.println("3. Department: ");
			String department = scanner.next();
			System.out.println("4. Credits: ");
			Integer credits = Integer.parseInt(scanner.next());
			System.out.println("5. Course Level: ");
			Integer courseLevel = Integer.parseInt(scanner.next());
			courseDao.insert(cId, name, department, credits, courseLevel);
		} else if (choice == 2) {
			System.out.println("1. Enter Course ID to edit: ");
			Integer cId = scanner.nextInt();
			System.out.println("2. Course Name");
			String name = scanner.next();
			System.out.println("4. Credits: ");
			Integer credits = scanner.nextInt();
			System.out.println("3. Department: ");
			String department = scanner.next();
			System.out.println("5. Course Level: ");
			Integer courseLevel = Integer.parseInt(scanner.next());

			courseDao.updateCourse(cId, name, department, credits, courseLevel);

		}

	}

	private void viewProfile() {
		System.out.println("Press 0 to Go Back");
		System.out.println("1. First Name  : " + administrator.getFirstName());
		System.out.println("2. Last Name   : " + administrator.getLastName());
		System.out.println("3. D.O.B       : " + administrator.getDob());
		System.out.println("4. Employee Id : " + administrator.getEmployee_id());

		String value = scanner.next();

		while (!value.equals("0")) {
			System.out.println("Only '0' is valid input.");
			System.out.println("Press 0 to Go Back");
			value = scanner.next();
		}

	}

	private void specialReqs() {

		System.out.println("Here is a list of all pending Special Permissions:");
		List<SpecialReq> specialPerms = specialPermDao.getAllPendingSpecialReqs();

		for (int i = 0; i < specialPerms.size(); i++) {
			System.out.println("Choice:" + i + " Class ID: " + specialPerms.get(i).getKey().getcId() + " Classroom ID: "
					+ specialPerms.get(i).getKey().getClassroomId() + " Student GPA: " + specialPerms.get(i).getGpa()
					+ " Order of enollment: " + specialPerms.get(i).getOrderNumber() + " Schedule ID: "
					+ specialPerms.get(i).getKey().getScheduleId() + " Student ID: "
					+ specialPerms.get(i).getKey().getStudentId());
		}
		System.out.println("Enter the choice number to remove a Student, -1: to just exit");
		Integer choice = Integer.parseInt(scanner.next());
		SpecialReq rowToDelete = specialPerms.get(choice);
		if (choice == 0) {
			specialPermDao.deleteSpecialPerm(rowToDelete.getKey().getStudentId(), rowToDelete.getKey().getScheduleId(),
					rowToDelete.getKey().getClassroomId());
			System.out.print("Student removed from list successfully");
		} else {
			return;
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
		
		System.out.println("8. Enter email");
		String email = scanner.next();
		
		studentDao.insert(Integer.parseInt(studentId), firstName, lastName, dob, Integer.parseInt(level),
				Integer.parseInt(resident), Integer.parseInt(amount), email);
	}

	void viewDetails() {
		System.out.println("Please Enter Student Id: ");
		String id = scanner.next();

		while (!id.matches("[0-9]+")) {
			System.out.println("Invalid Student Id");
			System.out.println("Please enter student id");
			id = scanner.next();
		}

		Student student = studentDao.getById(Integer.parseInt(id));

		if (student == null) {
			System.out.println("Student not found in database");
			return;
		}

		System.out.println("1. First Name :" + student.getFirstName());
		System.out.println("2. Last Name :" + student.getLastName());
		System.out.println("3. D.O.B(MM-DD-YYYY) :" + student.getDateOfBirth());
		System.out.println("4. Student’s Level :" + student.isLevel());
		System.out.println("5. Student’s Residency Status :" + student.getResidency());
		System.out.println("6. Amount Owed(if any) :" + student.getBill());
		System.out.println("7. GPA :" + student.getGpa());
		System.out.println("8. Phone :" + student.getPhone());
		System.out.println("9. Email :" + student.getEmail());
		System.out.println("10. Address :" + student.getAddress());
		System.out.println("Press 0 To Go Back To Previous Menu");
		System.out.println("Press 1 to enter grade");
		String value = scanner.next();
		
		if(value.equals("0"))
			return;
		
		if(value.equals("1")) {
			//Enter grade
			
			List<CourseListing> courseListings = courseListingDao.getRegisteredCourseByStudent(student.getStudentID());
			for(CourseListing courseListing : courseListings) {
				System.out.println(courseListing.getKey().getCid() +" : " +courseListing.getName());
			}
			
			System.out.println("Entert course ID to update GPA");
			String cid = scanner.next();
			
			Enrollments enrollments = enrollmentDao.getByStudentAndCourse(student.getStudentID(), Integer.parseInt(cid));
			
			if(enrollments == null) {
				System.out.println("Invalid course selection");
			} else {
				System.out.println("Existing GPA is " + enrollments.getGpa());
				System.out.println("Enter new GPA");
				String gpa = scanner.next();
				enrollmentDao.updateGPA(student.getStudentID(), Integer.parseInt(cid), Float.parseFloat(gpa));
				System.out.println("Successfully updated GPA");
				
				studentDao.setGPA(student.getStudentID());
			}
		}
	}

	public void viewAddCourse() {
		System.out.println("Select Appropriate Menu Option: ");
		System.out.println("0. Go Back To Previous Menu ");
		System.out.println("1. View Course Offerings ");
		System.out.println("2. Add New Course Offering ");

		String value = scanner.next();

		switch (value) {
		case "2":
			addNewCourseOffering();
			break;

		}
	}

	void addNewCourseOffering() {
		System.out.println("1. Enter Course Id : ");
		String cid = scanner.next();

		while (true) {
			if (cid.matches("[0-9]+")) {
				Course course = courseDao.getById(Integer.parseInt(cid));
				if (course != null)
					break;
			}

			System.out.println("Invalid course Id enter again");
			cid = scanner.next();
		}

		System.out.println("2. Enter Semester :");
		String semester = scanner.next();
		System.out.println("2. Enter Year :");
		String year = scanner.next();

		System.out.println("3. Enter Faculty Name​(can be multiple) : ");
		System.out.println("Type 'Complete' to finish");

		List<String> faculty = new ArrayList<>();

		while (true) {
			String complete = scanner.next();
			if (complete.equalsIgnoreCase("Complete")) {
				break;
			}

			faculty.add(complete);
		}

		List<Integer> facultyId = new ArrayList<>();

		for (String name : faculty) {
			Faculty f = facultyDao.getByName(name);
			if (f == null) {
				facultyDao.insert(name);
				f = facultyDao.getByName(name);
			}

			facultyId.add(f.getFid());
		}

		System.out.println("4. Enter Days Of the Week : ");
		System.out.println("Enter number corresponding to day seperated by ',' without space");
		System.out.println("1. Monday, 2. Tueday , 3.Wednesday, 4. Thursday, 5. Friday");
		String days = scanner.next();

		Set<Integer> daySet = new HashSet<>(
				Arrays.stream(days.split(",")).map(Integer::parseInt).collect(Collectors.toList()));

		int monday = daySet.contains(1) ? 1 : 0;
		int tuesday = daySet.contains(2) ? 1 : 0;
		int wednesday = daySet.contains(3) ? 1 : 0;
		int thursday = daySet.contains(4) ? 1 : 0;
		int friday = daySet.contains(5) ? 1 : 0;

		System.out.println("Enter Class Start Time (HH:mm) 24 hrs format:");
		String startTime = scanner.next();
		String sHour = startTime.split(":")[0];
		String smin = startTime.split(":")[1];

		System.out.println("Enter Class End Time (HH:mm) 24 hrs format:");
		String endTime = scanner.next();
		String eHour = endTime.split(":")[0];
		String emin = endTime.split(":")[1];

		Integer sId = scheduleDao.getSchedule(monday, tuesday, wednesday, thursday, friday, Integer.parseInt(sHour),
				Integer.parseInt(smin), Integer.parseInt(eHour), Integer.parseInt(emin));

		if (sId == null) {
			scheduleDao.insert(monday, tuesday, wednesday, thursday, friday, Integer.parseInt(sHour),
					Integer.parseInt(smin), Integer.parseInt(eHour), Integer.parseInt(emin));

			sId = scheduleDao.getSchedule(monday, tuesday, wednesday, thursday, friday, Integer.parseInt(sHour),
					Integer.parseInt(smin), Integer.parseInt(eHour), Integer.parseInt(emin));
		}
		System.out.println("Enter class room id");
		String classroomId = scanner.next();

		System.out.println("7. Class Size :");
		String classSize = scanner.next();

		System.out.println("8. Wait list Size : ");
		String waitListSize = scanner.next();

		offeringDao.insert(parseInt(cid), sId, parseInt(classroomId), parseInt(classSize), parseInt(waitListSize),
				parseInt(classSize) + parseInt(waitListSize), semester, parseInt(year));
		for (Integer fId : facultyId) {
			facultyGroupDao.insert(parseInt(cid), sId, parseInt(classroomId), fId, semester, parseInt(year));
		}
	}

	static int parseInt(String s) {
		return Integer.parseInt(s);
	}
}
