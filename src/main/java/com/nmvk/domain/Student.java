package com.nmvk.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entity class representing Schedule table.
 * 
 * @author Raghav
 *
 */
@Entity
public class Student implements Serializable {

	/*
	 * Added to ignore serialization warning.
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "STUDENT_ID")
	private int studentID;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "F_NAME")
	private String firstName;
	
	@Column(name = "L_NAME")
	private String lastName;
	
	@Column(name = "DEPARTMENT")
	private String department;
	
	@Column(name = "GPA")
	private int gpa;	
	
	@Column(name = "BILL")
	private int bill;
	
	@Column(name = "LVL")
	private boolean level;
	
	@Column(name = "PHONE")
	private String phone;
	
	@Column(name = "RESIDENCY")
	private boolean residency;

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getGpa() {
		return gpa;
	}

	public void setGpa(int gpa) {
		this.gpa = gpa;
	}

	public int getBill() {
		return bill;
	}

	public void setBill(int bill) {
		this.bill = bill;
	}

	public boolean isLevel() {
		return level;
	}

	public void setLevel(boolean level) {
		this.level = level;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isResidency() {
		return residency;
	}

	public void setResidency(boolean residency) {
		this.residency = residency;
	}
	
	
	
}
