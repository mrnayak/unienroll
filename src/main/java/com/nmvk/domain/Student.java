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
	private boolean email;
	
	@Column(name = "F_NAME")
	private boolean firstName;
	
	@Column(name = "L_NAME")
	private boolean lastName;
	
	@Column(name = "DEPARTMENT")
	private boolean department;
	
	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public boolean isEmail() {
		return email;
	}

	public void setEmail(boolean email) {
		this.email = email;
	}

	public boolean isFirstName() {
		return firstName;
	}

	public void setFirstName(boolean firstName) {
		this.firstName = firstName;
	}

	public boolean isLastName() {
		return lastName;
	}

	public void setLastName(boolean lastName) {
		this.lastName = lastName;
	}

	public boolean isDepartment() {
		return department;
	}

	public void setDepartment(boolean department) {
		this.department = department;
	}

	public boolean isGpa() {
		return gpa;
	}

	public void setGpa(boolean gpa) {
		this.gpa = gpa;
	}

	public boolean isBill() {
		return bill;
	}

	public void setBill(boolean bill) {
		this.bill = bill;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public int getResidency() {
		return residency;
	}

	public void setResidency(int residency) {
		this.residency = residency;
	}

	@Column(name = "GPA")
	private boolean gpa;	
	
	@Column(name = "BILL")
	private boolean bill;
	
	@Column(name = "LVL")
	private int level;
	
	@Column(name = "PHONE")
	private int phone;
	
	@Column(name = "RESIDENCY")
	private int residency;	
	
}
