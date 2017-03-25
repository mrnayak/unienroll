package com.nmvk.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class Offering implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cid")
	Integer cId;
	
	@Id
	@Column(name = "sched_id")
	Integer scheduleId;
	
	@Id
	@Column(name = "classroom_id")
	Integer classroomId;
	
	@Column(name = "max")
	Integer max;
	
	@Column(name = "waitlist")
	Integer waitList;
	
	@Column(name = "remaining")
	Integer remaining;
	
	@Column(name = "sem")
	String semester;
	
	@Column(name = "year")
	Integer year;

	public Integer getcId() {
		return cId;
	}

	public void setcId(Integer cId) {
		this.cId = cId;
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

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Integer getWaitList() {
		return waitList;
	}

	public void setWaitList(Integer waitList) {
		this.waitList = waitList;
	}

	public Integer getRemaining() {
		return remaining;
	}

	public void setRemaining(Integer remaining) {
		this.remaining = remaining;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
}
