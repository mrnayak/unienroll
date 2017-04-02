package com.nmvk.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SemKey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public SemKey() {
		
	}
	@Column(name = "SEM")
	String sem;

	@Column(name = "YEAR")
	int year;

	public String getSem() {
		return sem;
	}

	public void setSem(String sem) {
		this.sem = sem;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}