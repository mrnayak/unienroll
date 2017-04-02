package com.nmvk.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Enrollments implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "STUDENT_ID")
	Integer studentId;
	
	@Column(name = "GPA")
	Float gpa;
	
	@Id
	@Column(name = "SCHED_ID")
	Integer scheduleId;
	
	@Id
	@Column(name = "CLASSROOM_ID")
	Integer classroomId;
	
	@Id
	@Column(name = "CID") // Course ID
	Integer cId;
	
	@Column(name = "ORDER_NUM") // Order in which a student gets enrolled. Should update when some student drops a course
	Integer orderNumber;

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Float getGpa() {
		return gpa;
	}

	public void setGpa(Float gpa) {
		this.gpa = gpa;
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

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}	
	
}
