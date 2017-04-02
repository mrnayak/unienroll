package com.nmvk.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MainSemYearKey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MainSemYearKey() {
		
	}
	public Integer getCid() {
		return cid;
	}


	public void setCid(Integer cid) {
		this.cid = cid;
	}


	public Integer getSched_id() {
		return sched_id;
	}


	public void setSched_id(Integer sched_id) {
		this.sched_id = sched_id;
	}


	public Integer getClassroom_id() {
		return classroom_id;
	}


	public void setClassroom_id(Integer classroom_id) {
		this.classroom_id = classroom_id;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Column(name = "CID")
	Integer cid;
	
	@Column(name = "SCHED_ID")
	Integer sched_id;
	
	
	@Column(name = "CLASSROOM_ID")
	Integer classroom_id;
	
	@Column(name="SEM")
	String sem;
	
	@Column(name="YEAR")
	int year;
}