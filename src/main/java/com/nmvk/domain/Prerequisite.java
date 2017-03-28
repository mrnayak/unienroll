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
public class Prerequisite implements Serializable {

	/*
	 * Added to ignore serialization warning.
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "CID")
	private int courseID;
	
	@Id
	@Column(name = "REQ_CID")
	private String requiredCourseID;

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public String getRequiredCourseID() {
		return requiredCourseID;
	}

	public void setRequiredCourseID(String requiredCourseID) {
		this.requiredCourseID = requiredCourseID;
	}
	
}

