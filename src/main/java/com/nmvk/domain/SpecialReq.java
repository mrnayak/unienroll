package com.nmvk.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.hibernate.annotations.Type;


@Entity
public class SpecialReq implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	EnrollmentKey key;
	
	@Column(name = "ADMIN_ID")
	Integer employeeId;
	
	@Column(name = "IS_APPROVED")
	@Type(type = "com.nmvk.type.BooleanType")
	boolean is_approved;
	
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
	
	

	public Integer getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}


	public boolean isIs_approved() {
		return is_approved;
	}


	public void setIs_approved(boolean is_approved) {
		this.is_approved = is_approved;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Column(name = "GPA")
	Float gpa;
	
		
	@Column(name = "ORDER_NUM") // Order in which a student gets enrolled. Should update when some student drops a course
	Integer orderNumber;

		
}
