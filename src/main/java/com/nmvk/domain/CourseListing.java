package com.nmvk.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

@Entity
public class CourseListing implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	MainKey key;
	
	public MainKey getKey() {
		return key;
	}


	public void setKey(MainKey key) {
		this.key = key;
	}


	@Column(name = "NAME")
	String name;
	
	@Column(name = "DEPARTMENT")
	String department;
	
	@Column(name = "CREDIT")
	Integer credit;
	
	@Column(name = "REMAINING")
	Integer remaining;
	
	public Integer getRemaining() {
		return remaining;
	}


	public void setRemaining(Integer remaining) {
		this.remaining = remaining;
	}


	@Type(type = "com.nmvk.type.BooleanType")
	@Column(name = "MON")
	boolean mon;
	
	@Type(type = "com.nmvk.type.BooleanType")
	@Column(name = "TUE")
	boolean tue;
	
	@Type(type = "com.nmvk.type.BooleanType")
	@Column(name = "WED")
	boolean wed;
	
	@Type(type = "com.nmvk.type.BooleanType")
	@Column(name = "THU")
	boolean thu;
	
	@Type(type = "com.nmvk.type.BooleanType")
	@Column(name = "FRI")
	boolean fri;
	
	@Column(name = "START_HOUR")
	Integer start_hour;
	
	@Column(name = "START_MIN")
	Integer start_min;
	
	@Column(name = "END_HOUR")
	Integer end_hour;
	

	@Column(name = "END_MIN")
	Integer end_min;


	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public Integer getCredit() {
		return credit;
	}


	public void setCredit(Integer credit) {
		this.credit = credit;
	}


	public boolean isMon() {
		return mon;
	}


	public void setMon(boolean mon) {
		this.mon = mon;
	}


	public boolean isTue() {
		return tue;
	}


	public void setTue(boolean tue) {
		this.tue = tue;
	}


	public boolean isWed() {
		return wed;
	}


	public void setWed(boolean wed) {
		this.wed = wed;
	}


	public boolean isThu() {
		return thu;
	}


	public void setThu(boolean thu) {
		this.thu = thu;
	}


	public boolean isFri() {
		return fri;
	}


	public void setFri(boolean fri) {
		this.fri = fri;
	}


	public Integer getStart_hour() {
		return start_hour;
	}


	public void setStart_hour(Integer start_hour) {
		this.start_hour = start_hour;
	}


	public Integer getStart_min() {
		return start_min;
	}


	public void setStart_min(Integer start_min) {
		this.start_min = start_min;
	}


	public Integer getEnd_hour() {
		return end_hour;
	}


	public void setEnd_hour(Integer end_hour) {
		this.end_hour = end_hour;
	}


	public Integer getEnd_min() {
		return end_min;
	}


	public void setEnd_min(Integer end_min) {
		this.end_min = end_min;
	}
	
}
