package com.nmvk.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author ani
 *
 */
@Entity
public class SemWiseGPA implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "GPA")
	Float GPA;

	
	@Column(name = "YEAR")
	String YEAR;
	
	

	public Float getGPA() {
		return GPA;
	}

	public void setGPA(Float gpa) {
		this.GPA = gpa;
	}

	public String getYear() {
		return YEAR;
	}

	public void setYEAR(String year) {
		this.YEAR = year;
	}




}
