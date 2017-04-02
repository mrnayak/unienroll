package com.nmvk.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EnrollmentKey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public EnrollmentKey() {
		
	}
	
	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getSem() {
		return sem;
	}

	public void setSem(String sem) {
		this.sem = sem;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Integer getClassroomId() {
		return classroomId;
	}

	public void setClassroomId(Integer classroomId) {
		this.classroomId = classroomId;
	}

	public Integer getcId() {
		return cId;
	}

	public void setcId(Integer cId) {
		this.cId = cId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column(name = "STUDENT_ID")
	Integer studentId;
	
	@Column(name = "SEM")
	String sem;
	
	@Column(name = "YEAR")
	String year;
	
	@Column(name = "SCHED_ID")
	Integer scheduleId;
	
	@Column(name = "CLASSROOM_ID")
	Integer classroomId;
		
	@Column(name = "CID") // Course ID
	Integer cId;


}