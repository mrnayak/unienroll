package com.nmvk.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

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
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "GPA")
	private float gpa;	
	
	@Column(name = "BILL")
	private int bill;
	
	@Column(name = "LVL")
	@Type(type = "com.nmvk.type.BooleanType")
	private boolean level;
	
	@Column(name = "PHONE")
	private String phone;
	
	@Column(name = "DOB")
	private String dateOfBirth;
	
	@Column(name = "RESIDENCY")
	private int residency;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getResidency() {
		return residency;
	}

	public void setResidency(int residency) {
		this.residency = residency;
	}

	public float getGpa() {
		return gpa;
	}

	public void setGpa(float gpa) {
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

	
}
