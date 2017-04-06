package com.nmvk.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nmvk.dao.AppUserDao;
import com.nmvk.dao.CourseDao;
import com.nmvk.dao.CourseListingDao;
import com.nmvk.dao.EnrollmentDao;
import com.nmvk.dao.FacultyDao;
import com.nmvk.dao.OfferingDao;
import com.nmvk.dao.PreReqDao;
import com.nmvk.dao.PreconditionDao;
import com.nmvk.dao.SemWiseGPADao;
import com.nmvk.dao.SemesterDao;
import com.nmvk.dao.SpecialPermDao;
import com.nmvk.dao.StudentDao;
import com.nmvk.domain.AppUser;
import com.nmvk.domain.Course;
import com.nmvk.domain.CourseListing;
import com.nmvk.domain.Enrollments;
import com.nmvk.domain.Faculty;
import com.nmvk.domain.Offering;
import com.nmvk.domain.Preconditionrequirement;
import com.nmvk.domain.Prerequisite;
import com.nmvk.domain.SemWiseGPA;
import com.nmvk.domain.Semester;
import com.nmvk.domain.Student;

@Service
public class StudentService {
	
	@Autowired
	Scanner scanner;
	
	@Autowired
	EnrollmentDao enrollmentDao;
	
	@Autowired
	PreReqDao prereqDao;
	
	@Autowired
	FacultyDao facultyDao;
	
	Student student = new Student();
	
	@Autowired
	SemesterDao semesterDao;
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	CourseListingDao courseListingDao;
	
	@Autowired
	StudentDao studentDao;
	
	@Autowired
	SemWiseGPADao semWiseGPADao;
	
	@Autowired
	OfferingDao offeringDao; 
	
	@Autowired
	CourseDao courseDao;

	@Autowired
	SpecialPermDao specialPermDao;
	
	@Autowired
	PreconditionDao preconditionDao;
	
	@Autowired
	AppUserDao appUserDao;
	
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
			student=studentDao.getStudentInfo(loginService.appUser.getUserName());
			System.out.println("\n**********Main menu**********");
			System.out.println("1. View/Edit Profile "); //done
			System.out.println("2. View Offerings");
			System.out.println("3. View Registered courses");
			System.out.println("4. View Waitlisted courses ");
			System.out.println("5. View Pending courses ");
			System.out.println("6. View grades");
			System.out.println("7. View/Pay Bill ");
			System.out.println("8. Logout");
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
				viewRegisteredCourses();
				continue;
			case "4":
				viewWaitlistedCourses();
				continue;
			case "5":
				viewPendingCourses();
				continue;
			case "6":
				viewGrades();
				continue;		
			case "7":
				viewPayBill();
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
	 * Press 0 to Go Back 1. First Name : ##### 2. Last Name : ##### 3. Email :
	 * ##### 4. Phone : #####  5. Level 6. Status
	 */
	private void viewEditProfile(boolean canEdit) {
		System.out.println("\n**********View/Edit Profile**********");
		System.out.println("Press 0 to Go Back");
		displayProfile();
		
		if(canEdit){
			System.out.println("Please enter choice of number 1/2/3/4 to edit that particular item or to update password press \'p\': ");
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
				case "p":
				case "P":
					System.out.println("Enter old password : ");
					String oldPassword = scanner.next();
					System.out.println("Enter new password : ");
					String newPassword = scanner.next();
					
					if(validateAndUpdatePassword(oldPassword, newPassword)){
						System.out.println("*********Password successfully updated*********");
					}
					else{
						System.out.println("###### ERROR: Failed to update password, check if old password entered is correct or if you mistakenly entered blank for new password ######");
						System.out.println("\t######Returning######");
					}
					System.out.println("\tPress any key to continue");
					String val = scanner.next();
					return;
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
		System.out.println("Open Semesters:");
		List<Semester> openSem = semesterDao.getActiveSem();
		
		int counter = 1;
		for (Semester sem : openSem) {

			System.out.println(String.valueOf(counter)+":"+sem.getKey().getSem()+" "+sem.getKey().getYear());
			counter+=1;
		}
		counter--;
		System.out.println("\n\tTo see offerings from above semesters choose a number between 1 & "+ counter + ". If you don't want press 0 to continue");

		String semResponse = scanner.next();
		Integer semResponseInt = 1;
		semResponseInt = validateIntScanWithLimit(semResponse, counter);
//		if(semResponseInt == 0){
//			return;
//		}
		
		Semester currentSem = openSem.get(semResponseInt -1);
		
		System.out.println(":"+currentSem.getKey().getSem()+" "+currentSem.getKey().getYear());
		
		System.out.println("Available courses: ");
		List<CourseListing> courseList=courseListingDao.getOfferingsBySem(currentSem.getKey().getSem(),currentSem.getKey().getYear(), student.getStudentID());
		
		counter = 1;
		for (CourseListing courseEnt : courseList) {
			String schedule=courseEnt.isMon()?"M":"";
			schedule+=courseEnt.isTue()?"T":"";
			schedule+=courseEnt.isWed()?"W":"";
			schedule+=courseEnt.isThu()?"Th":"";
			schedule+=courseEnt.isFri()?"F":"";
			schedule+=String.valueOf(" "+courseEnt.getStart_hour())+":"+String.valueOf(courseEnt.getStart_min())+"-"+String.valueOf(courseEnt.getEnd_hour())+":"+String.valueOf(courseEnt.getEnd_min());
			
			List<Faculty> facultyList=facultyDao.getFacultyListForCourse(courseEnt.getKey().getCid(), courseEnt.getKey().getSched_id(), courseEnt.getKey().getClassroom_id());
			String facultytring="";
			for(Faculty faculty:facultyList){
				facultytring+=faculty.getName()+" ";
			}
			
			System.out.println(String.valueOf(counter)+":"+courseEnt.getName()+"\t\t"+courseEnt.getDepartment()+" "+courseEnt.getRemaining()+" "+schedule +" "+facultytring);
			
			counter+=1;
		}
		counter--;
		System.out.println("\n\tTo enroll for a course listed above enter a number between 1 & "+ counter + ". If you don't want to enroll press 0 to go back or continue");
		String addCourseValue = scanner.next();
		Integer addCourseValueInt = 1;
		addCourseValueInt = validateIntScanWithLimit(addCourseValue, counter);
		
		// if value is 0 skip below if condition
		if(addCourseValueInt != 0){
			
			
			CourseListing courseToRegister = courseList.get(addCourseValueInt - 1);
			
			// TODO: Add condition to check if all preconditions are met
			boolean isEnrolled = checkIfEnrolled(student.getStudentID(), courseToRegister, currentSem); 
			if(!isEnrolled){
				boolean creditLimitExceeded=false;
				if(!creditLimitExceeded){
					
					
					int status=0;
					int prereqstatus=checkPreReqCourses(courseToRegister);
					int gpaStatus=checkGPACOndition(courseToRegister);
					int specialPermStatus=checkSpecialPerm(courseToRegister);
					
					if(prereqstatus==0){
						if(gpaStatus==0){
							if(specialPermStatus==0){
								status=0;
							}
							else{
								System.out.println("###########Special Permission required###########");
								System.out.println("###########Request Sent###########");
								specialPermDao.addSpecialPerm(student.getStudentID(),courseToRegister.getKey().getSched_id() , courseToRegister.getKey().getClassroom_id(), courseToRegister.getKey().getCid(),currentSem.getKey().getSem(),currentSem.getKey().getYear());
								status=1;
							}
						}
						else{
							System.out.println("###########GPA requirement not met###########");
							status=1;
						}
					}
					else{
						System.out.println("###########Prerequisites not met###########");
						status=1;
					}
					
					
					if (status==0){
						
						if(enrollForCourse(student.getStudentID(), courseToRegister, currentSem)){
							System.out.println("***********Enrollment successful***********");
						}
						else{
							System.out.println("###########Enrollment not successful###########");
						}
					}	
				}
				else{
					System.out.println("Credit limit exceed. please drop a course");
				}
			}
			else{
			//	TODO: If not print saying student can't enroll since the precondition are not met or this clashes with his other courses
			System.out.println("Already Enrolled");
			}
		}	
	}
	
	private int checkPreReqCourses(CourseListing courseListing){
		List<Prerequisite> preqCourse = prereqDao.getPreReqCourse(courseListing.getKey().getCid());
		if(preqCourse.size() != 0){
			boolean preReqStatus=true;
			for(Prerequisite preReq: preqCourse){
				
				if(enrollmentDao.checkIfStudentRegistered(student.getStudentID(),preReq.getKey().getReq_cid())==null){
					preReqStatus=false;
					break;
				}
			}
			if(!preReqStatus){
				return 1;	
			}
			else{
				return 0;
			}
		}
		return 0;
	}
	
	private int checkGPACOndition(CourseListing courseListing){
		Preconditionrequirement preconditionGPA = preconditionDao.getGPAReq(courseListing.getKey().getCid()); 
		
		if(preconditionGPA==null){
			return 0;
		}
		else if(preconditionGPA.getGpa()<(studentDao.getOverallAvgGPA(student.getStudentID())==null?0.0:studentDao.getOverallAvgGPA(student.getStudentID()))){
			return 0;
			}
		else{
			return 1;
		}
	}
	
	private int checkSpecialPerm(CourseListing courseListing){
		
		Preconditionrequirement preconditionList = preconditionDao.getSpecialPerm(courseListing.getKey().getCid());
		if(preconditionList!=null){
			return 1;
		}
		else{
			return 0;
		}
		
	}
	
	/*
		View Courses and Status
		Press 0 To Go Back To Previous Menu
	*/
	private void viewRegisteredCourses(){
		
		//Student_id

		int student_id=1;
		System.out.println("Open Semesters:");
		List<Semester> openSem = semesterDao.getAllSem();
		
		int counter = 1;
		for (Semester sem : openSem) {

			System.out.println(String.valueOf(counter)+":"+sem.getKey().getSem()+" "+sem.getKey().getYear());
			counter+=1;
		}
		counter--;
		
		System.out.println("\n\tTo see registered courses from above semesters choose a number between 1 & "+ counter);

		String semResponse = scanner.next();
		Integer semResponseInt = 1;
		semResponseInt = validateIntScanWithLimit(semResponse, counter);

		Semester currentSem = openSem.get(semResponseInt -1);
				
		System.out.println("Registered courses: ");
		
		List<CourseListing> registeredCourses = courseListingDao.getRegisteredCourseBySem(currentSem.getKey().getSem(), currentSem.getKey().getYear(), student.getStudentID());
		counter = 1;
		for (CourseListing courseEnt : registeredCourses) {
			String schedule=courseEnt.isMon()?"M":"";
			schedule+=courseEnt.isTue()?"T":"";
			schedule+=courseEnt.isWed()?"W":"";
			schedule+=courseEnt.isThu()?"Th":"";
			schedule+=courseEnt.isFri()?"F":"";
			schedule+=String.valueOf(" "+courseEnt.getStart_hour())+":"+String.valueOf(courseEnt.getStart_min())+"-"+String.valueOf(courseEnt.getEnd_hour())+":"+String.valueOf(courseEnt.getEnd_min());
		
			System.out.println(String.valueOf(counter)+":"+courseEnt.getName()+"\t\t"+courseEnt.getDepartment()+" "+courseEnt.getRemaining()+" "+schedule);
			counter+=1;
		}
		counter--;
		if(counter < 1){
			return;
		}
		
		System.out.println("\n\tTo Drop for a course listed above enter a number between 1 & "+ counter + ". If you don't want to drop press 0 to go back or continue");
		String dropCourseValue = scanner.next();
		Integer dropCourseValueInt = 1;
		dropCourseValueInt = validateIntScanWithLimit(dropCourseValue, counter);
		if(dropCourseValueInt == 0){
			return;
		}
		CourseListing courseToDrop = registeredCourses.get(dropCourseValueInt-1);
		
		System.out.println("Do you want to drop the course for sure? yes/No");
		String value = scanner.next();
		boolean canDrop = false; // TODO: Make sure he is enrolled before dropping, or drop deadline is not over
		if(value.equals("yes") || value.equals("Yes") || value.equals("YES") || value.equals("Y") || value.equals("y") ){
			canDrop = true;
		}
		if(canDrop){
			if(dropCourse(student.getStudentID(), courseToDrop, currentSem)){
				System.out.println("*********** Drop successful ***********");
			}
			else{
				System.out.println("########### Drop not successful ###########");
			}
		}
		else{
			//TODO: If not print saying student can't drop, reason can be the drop date deadline is over
			System.out.println("Can't drop");
		}
	}
	
	private void viewWaitlistedCourses(){
		
		//Student_id
		int student_id=1;
		System.out.println("Open Semesters: ");
		List<Semester> openSem = semesterDao.getActiveSem();
		
		int counter = 1;
		for (Semester sem : openSem) {

			System.out.println(String.valueOf(counter)+":"+sem.getKey().getSem()+" "+sem.getKey().getYear());
			counter+=1;
		}		
		counter--;
		
		System.out.println("\n\tTo see your waitlisted courses from above semesters choose a number between 1 & "+ counter);
		String semResponse = scanner.next();
		
		Integer semResponseInt = 1;
		semResponseInt = validateIntScanWithLimit(semResponse, counter);
		Semester currentSem = openSem.get(semResponseInt -1);				
		System.out.println("Waitlisted courses: ");		
		
		List<CourseListing> wlCourses = courseListingDao.getWLourseBySem(currentSem.getKey().getSem(), currentSem.getKey().getYear(), student.getStudentID());
		counter = 1;
		for (CourseListing courseEnt : wlCourses) {
			String schedule=courseEnt.isMon()?"M":"";
			schedule+=courseEnt.isTue()?"T":"";
			schedule+=courseEnt.isWed()?"W":"";
			schedule+=courseEnt.isThu()?"Th":"";
			schedule+=courseEnt.isFri()?"F":"";
			schedule+=String.valueOf(" "+courseEnt.getStart_hour())+":"+String.valueOf(courseEnt.getStart_min())+"-"+String.valueOf(courseEnt.getEnd_hour())+":"+String.valueOf(courseEnt.getEnd_min());
		
		
			System.out.println(String.valueOf(counter)+":"+courseEnt.getName()+"\t\t"+courseEnt.getDepartment()+" "+courseEnt.getRemaining()+" "+schedule);
			counter+=1;
		}
		counter--;
		if(counter < 1){
			return;
		}
		System.out.println("\n\tTo Drop for a course listed above enter a number between 1 & "+ counter + ". If you don't want to drop press 0 to go back or continue");
		String dropCourseValue = scanner.next();
		Integer dropCourseValueInt = 1;
		dropCourseValueInt = validateIntScanWithLimit(dropCourseValue, counter);
		if(dropCourseValueInt == 0){
			return;
		}
		CourseListing courseToDrop = wlCourses.get(dropCourseValueInt-1);
		
		System.out.println("Do you want to drop the course for sure? yes/No");
		String value = scanner.next();
		boolean canDrop = false; // TODO: Make sure he is enrolled before dropping, or drop deadline is not over
		if(value.equals("yes") || value.equals("Yes") || value.equals("YES") || value.equals("Y") || value.equals("y") ){
			canDrop = true;
		}
		if(canDrop){
			if(dropCourse(student.getStudentID(), courseToDrop, currentSem)){
				System.out.println("*********** Drop successful ***********");
			}
			else{
				System.out.println("########### Drop not successful ###########");
			}
		}
		else{
			//TODO: If not print saying student can't drop, reason can be the drop date deadline is over
			System.out.println("Can't drop");
		}
		
	}
	
private void viewPendingCourses(){
	
	//Student_id
	int student_id=student.getStudentID();
	System.out.println("Open Semesters, choose: ");
	List<Semester> openSem = semesterDao.getActiveSem();
	
	int counter = 1;
	for (Semester sem : openSem) {

		System.out.println(String.valueOf(counter)+":"+sem.getKey().getSem()+" "+sem.getKey().getYear());
		counter+=1;
	}
	String semResponse = scanner.next();
	
	Semester currentSem = openSem.get(Integer.valueOf(semResponse)-1);
	List<CourseListing> penCourses = courseListingDao.getPendingCourseBySem(currentSem.getKey().getSem(), currentSem.getKey().getYear(), student.getStudentID());
	counter = 1;
	for (CourseListing courseEnt : penCourses) {
		String schedule=courseEnt.isMon()?"M":"";
		schedule+=courseEnt.isTue()?"T":"";
		schedule+=courseEnt.isWed()?"W":"";
		schedule+=courseEnt.isThu()?"Th":"";
		schedule+=courseEnt.isFri()?"F":"";
		schedule+=String.valueOf(" "+courseEnt.getStart_hour())+":"+String.valueOf(courseEnt.getStart_min())+"-"+String.valueOf(courseEnt.getEnd_hour())+":"+String.valueOf(courseEnt.getEnd_min());
	
	
	System.out.println(String.valueOf(counter)+":"+courseEnt.getName()+" "+courseEnt.getDepartment()+" "+courseEnt.getRemaining()+" "+schedule);
	
	
	counter+=1;
	}
	System.out.println("press 0 to go back");
	String response = scanner.next();
	while(Integer.valueOf(response)!=0){
		System.out.println("invalid.press 0 to go back");
		response = scanner.next();
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
		System.out.println("Your Student id is");
		Integer sid = student.getStudentID();
		Float GPA = studentDao.getOverallAvgGPA(sid);
		System.out.println("Your overall GPA is: "+GPA);
		System.out.println("Here is a break down of your GPA's");
		List<SemWiseGPA> GPAs = semWiseGPADao.getSemWiseGPA(sid);
		for(int i = 0; i<GPAs.size();i++){
			System.out.println("GPA: "+GPAs.get(i).getGPA()+" Year: "+ GPAs.get(i).getYear());
		}
		
		//USING OBJECTS TO STORE INDUVIDUAL COURSES AND THEIR GPAS
		System.out.println("GPA's course wise:-");
		List<Object[]> cGPAs = semWiseGPADao.getAllCourseGPAs(sid);
		for(int i = 0;i<cGPAs.size();i++){
			System.out.println("GPA: "+cGPAs.get(i)[0]+" Subject: "+cGPAs.get(i)[1]);
		}
		
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
	
	
	//********************************************************************************************************************************************
	//********************************************************************************************************************************************
	/* Note: DAO related helpers goes below here. 
	 If we are adding new method which uses DAO we can refer if we already have that method below */
	//********************************************************************************************************************************************
	//********************************************************************************************************************************************

	
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
			System.out.println("***********SOME DB ERROR: FAILED TO UPDATE STUDENT***********");
			System.out.println(e);
			System.out.println("***********SOME DB ERROR: FAILED TO UPDATE STUDENT***********");
		}
		
	}
	

	private boolean enrollForCourse(int studentId, CourseListing courseToRegister, Semester currentSem){
		try{
			Integer courseId = courseToRegister.getKey().getCid();
			Course course=courseDao.getById(courseId);
			int credit=course.getCredit();
			
			if (credit==0){
				System.out.println("*********** Since this is a variable course, please enter credits (1-3) ***********\n");
				String creditString = scanner.next();
				Integer creditInt = validateIntScanWithLimit(creditString, 3);
				credit=creditInt;
			}
			
			Integer totalCreditRegistered=enrollmentDao.getRegisteredCredit(student.getStudentID(), currentSem.getKey().getSem(), String.valueOf(currentSem.getKey().getYear()));
			totalCreditRegistered=totalCreditRegistered==null?0:totalCreditRegistered;
			int limitCredit= enrollmentDao.getMaxCreditLimit(student.isLevel()?1:0,student.getResidency());
			if(totalCreditRegistered+credit>limitCredit){
				System.out.println("*********** Credit limit exceeded. drop a course ***********\n");
				return false;
			}
			
			Integer scheduleId = courseToRegister.getKey().getSched_id();
			Integer classRoomId = courseToRegister.getKey().getClassroom_id(); 
			//System.out.println("Offering offering = offeringDao.getByIds(courseId, scheduleId, classRoomId, currentSem.getKey().getSem(), currentSem.getKey().getYear());");
			//System.out.println(courseId+ "," + scheduleId + "," + classRoomId + "," + currentSem.getKey().getSem() + "," + currentSem.getKey().getYear());
			Offering offering = new Offering();
			try{
				offering = offeringDao.getByIds(courseId, scheduleId, classRoomId, currentSem.getKey().getSem(), currentSem.getKey().getYear());
			}
			catch(Exception e){
				System.out.println("***********SOME DB ERROR: FAILED TO GET OFFERING***********");
				System.out.println(e);
				System.out.println("***********SOME DB ERROR: FAILED TO GET OFFERING***********");
			}
			if(offering != null && offering.getRemaining() > 0){
				try{
					//System.out.println("offeringDao.decrementRemaining(courseId, scheduleId, classRoomId, currentSem.getKey().getSem(), currentSem.getKey().getYear());");
					offeringDao.decrementRemaining(courseId, scheduleId, classRoomId, currentSem.getKey().getSem(), currentSem.getKey().getYear());
				}
				catch(Exception e){
					System.out.println("***********SOME DB ERROR: FAILED TO ENROLL DECREASE REMAINING***********");
					System.out.println(e);
					System.out.println("***********SOME DB ERROR: FAILED TO ENROLL DECREASE REMAINING***********");
				}
				Integer orderNumber = offering.getMax() + offering.getWaitList() - offering.getRemaining() + 1;
				//System.out.println("Order Number : " + orderNumber);
				if(orderNumber > offering.getMax()){
					Integer waitlistOrder = orderNumber - offering.getMax();
					System.out.println("&&&&&&&&&&& You are being enrolled to waitlist position " + waitlistOrder +" out of "+ offering.getWaitList() +"&&&&&&&&&&&\n");
				}
				else{
					System.out.println("*********** Enrollment in progress ***********\n");
				}
				
				
				
				enrollmentDao.addToEnrollment(studentId, 
						courseToRegister.getKey().getSched_id(), 
						courseToRegister.getKey().getClassroom_id(), 
						courseToRegister.getKey().getCid(), 
						orderNumber,
						currentSem.getKey().getSem(),
						String.valueOf(currentSem.getKey().getYear()),
						credit);
//				if(updateBillForCourseEnroll(studentId)){
//					System.out.println("$$$$$$$$$ Student successfully billed $$$$$$$$$\n");
//				}
			}
			else{
				System.out.println("########### All seats are filled, can't enroll further ###########\n\n");
				return false;
			}			
		}
		//Catch IntegretyConstraintException and handle some how
		catch(Exception e){
			System.out.println("***********SOME DB ERROR: FAILED TO ENROLL A STUDENT***********");
			System.out.println(e);
			System.out.println("***********SOME DB ERROR: FAILED TO ENROLL A STUDENT***********");
		}
		return true;
	}
	
	private boolean checkIfEnrolled(int studentId, CourseListing courseToRegister, Semester currentSem){
		Enrollments enrollment = enrollmentDao.getByIds(studentId, 
				courseToRegister.getKey().getSched_id(), 
				courseToRegister.getKey().getClassroom_id(), 
				courseToRegister.getKey().getCid(),
				currentSem.getKey().getSem(),
				String.valueOf(currentSem.getKey().getYear()));
		if(enrollment == null){
			
			return false;
		}
		return true;		
	}
	
	private boolean dropCourse(int studentId, CourseListing courseToDrop, Semester currentSem){
		try{
			if(!currentSem.getStatus()) {
				System.out.println("*** Drop deadline has passed****");
				return false;
			}
			Integer courseId = courseToDrop.getKey().getCid();
			Integer scheduleId = courseToDrop.getKey().getSched_id();
			Integer classRoomId = courseToDrop.getKey().getClassroom_id(); 
			
			try{
				offeringDao.incrementRemaining(courseId, scheduleId, classRoomId, currentSem.getKey().getSem(), currentSem.getKey().getYear());
			}
			catch(Exception e){
				System.out.println("***********SOME DB ERROR: FAILED TO DROP COURSE, INCREASE REMAINING***********");
				System.out.println(e);
				System.out.println("***********SOME DB ERROR: FAILED TO DROP COURSE, INCREASE REMAINING***********");
				return false;
			}
			
			Integer orderNumber = enrollmentDao.getOrderNumber(studentId, 
					courseToDrop.getKey().getSched_id(), 
					courseToDrop.getKey().getClassroom_id(), 
					courseToDrop.getKey().getCid(), 
					currentSem.getKey().getSem(), 
					String.valueOf(currentSem.getKey().getYear()));
			//System.out.println("orderNumber of the student : " + orderNumber);
						
			enrollmentDao.dropEnrollment(studentId, 
					courseToDrop.getKey().getSched_id(), 
					courseToDrop.getKey().getClassroom_id(), 
					courseToDrop.getKey().getCid(), 
					currentSem.getKey().getSem(), 
					String.valueOf(currentSem.getKey().getYear()));
			
			try{
				enrollmentDao.decrementOrder(orderNumber,						 
						courseToDrop.getKey().getSched_id(), 
						courseToDrop.getKey().getClassroom_id(), 
						courseToDrop.getKey().getCid(), 
						currentSem.getKey().getSem(), 
						String.valueOf(currentSem.getKey().getYear()));
			}
			catch(Exception e){
				System.out.println("***********SOME DB ERROR: FAILED TO DECREMENT ORDER FOR ENROLLED LIST AFTER A DROP***********");
				System.out.println(e);
				System.out.println("***********SOME DB ERROR: FAILED TO DECREMENT ORDER FOR ENROLLED LIST AFTER A DROP***********");
			}
							
//			if(updateBillForCourseDrop(studentId)){
//				System.out.println("$$$$$$$$$ Student successfully unbilled $$$$$$$$$\n");
//			}
		
		}
		catch(Exception e){
			System.out.println("***********SOME DB ERROR: FAILED TO ENROLL A STUDENT***********");
			System.out.println(e);
			System.out.println("***********SOME DB ERROR: FAILED TO ENROLL A STUDENT***********");
			return false;
		}		
		return true;		
	}
	
	/*  Billing for Resident = $1000
	 *  Billing for Non-Resident + $1000
	 *  Billing for International + $1000
	 *  Billing for Under Graduate + $1000
	 *  Billing for Graduate + $1000
	 *  Change the settings below as per original prices
	*/
	private Integer getBillPerCourseForStudent(Student billedStudent){
		// Note: base amount for Resident & Undergraduate
		Integer amount = 2000;
		if(billedStudent.isLevel()){
			amount += 1000;
		}
		switch(billedStudent.getResidency()){
			case 1:
				// No change in bill
				break;
			case 2:
				amount += 1000;
				break;
			case 3:
				amount += 2000;
				break;
			default:
				amount += 2000;			
		}		
		return amount;		
	}
	
	private boolean updateBillForCourseEnroll(int studentId){
		Student billedStudent = student;
		if(studentId != student.getStudentID()){
			billedStudent = studentDao.getById(studentId);
		}
		billedStudent.setBill(billedStudent.getBill() + getBillPerCourseForStudent(billedStudent));
		updateStudent(billedStudent);
		return true;
	}
	
	private boolean updateBillForCourseDrop(int studentId){
		Student billedStudent = student;
		if(studentId != student.getStudentID()){
			billedStudent = studentDao.getById(studentId);
		}				
		billedStudent.setBill(billedStudent.getBill() - getBillPerCourseForStudent(billedStudent));				
		updateStudent(billedStudent);
		return true;
	}
	
	private Integer validateIntScanWithLimit(String scanString, int limit) {
		Integer scanInt = 1;
		while(!scanString.equals("0")){
			try{
				scanInt = Integer.parseInt(scanString);
				if(scanInt <= limit && scanInt > 0){
					break;
				}
				else{
					System.out.println("Invalid option entered, please enter again or Enter 0 to continue");
					scanString = scanner.next();
					continue;
				}
			}
			catch(NumberFormatException e){
				System.out.println("Invalid option entered, please enter again or Enter 0 to continue");
				scanString = scanner.next();
				continue;
			}
		}
		if(scanString.equals("0")){
			scanInt = 0;
		}
		return scanInt;
	}
	
	private boolean validateAndUpdatePassword(String oldPassword, String newPassword) {		
		AppUser appUser = appUserDao.getByUsernameAndPassWord(student.getEmail(), oldPassword);		
		if(appUser == null) {
			System.out.println("###### Wrong credentials entered ######");
			return false;
		}
		boolean passwordUpdated = false;
		try{
			appUserDao.updatePassword(student.getEmail(), newPassword);
			passwordUpdated = true;
		}
		catch(Exception e){
			System.out.println("***********SOME DB ERROR: FAILED TO UPDATE PASSWORD***********");
			System.out.println(e);
			System.out.println("***********SOME DB ERROR: FAILED TO UPDATE PASSWORD***********");
		}
		return passwordUpdated;
	}

	
// TODO: Should Update email in Student table and AppUser table	
//	private void updateEmail(Student student){
//		try{
//			studentDao.updateEmail(new Integer(student.getStudentID()), student.getEmail());	
//		}
//		catch(Exception e){
//			System.out.println("***********SOME DB ERROR: FAILED TO UPDATE STUDENT***********");
//			System.out.println(e);
//			System.out.println("***********SOME DB ERROR: FAILED TO UPDATE STUDENT***********");
//		}
//		
//	}
}