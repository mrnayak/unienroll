package com.nmvk.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class FacultyGroup implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	FacultyGroupKey facultyGroupKey;

	public FacultyGroupKey getFacultyGroupKey() {
		return facultyGroupKey;
	}

	public void setFacultyGroupKey(FacultyGroupKey facultyGroupKey) {
		this.facultyGroupKey = facultyGroupKey;
	}
}
