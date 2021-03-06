package com.nmvk.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Enrollments implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	EnrollmentKey key;
	
	@Column(name = "GPA")
	Float gpa;
		
	@Column(name = "ORDER_NUM") // Order in which a student gets enrolled. Should update when some student drops a course
	Integer orderNumber;

	@Column(name="CREDIT")
	Integer credit;
	
	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public EnrollmentKey getKey() {
		return key;
	}

	public void setKey(EnrollmentKey key) {
		this.key = key;
	}

	public Float getGpa() {
		return gpa;
	}

	public void setGpa(Float gpa) {
		this.gpa = gpa;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
