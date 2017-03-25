package com.nmvk.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Type;


/**
 * Entity class representing Schedule table.
 * 
 * @author Raghav
 *
 */
@Entity
public class Schedule implements Serializable {

	/*
	 * Added to ignore serialization warning.
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SID")
	private int scheduleID;
	
	@Type(type = "true_false")
	@Column(name = "MON")
	private boolean monday;
	
	@Type(type = "true_false")
	@Column(name = "TUE")
	private boolean tuesday;
	
	@Type(type = "true_false")
	@Column(name = "WED")
	private boolean wednesday;
	
	@Type(type = "true_false")
	@Column(name = "THU")
	private boolean thursday;
	
	@Type(type = "true_false")
	@Column(name = "FRI")
	private boolean friday;
	
	@Column(name = "START_HOUR")
	private int startHour;
	
	@Column(name = "START_MIN")
	private int startMinute;
	
	@Column(name = "END_HOUR")
	private int endHour;
	
	@Column(name = "END_MIN")
	private int endMinute;
	
	public boolean isMonday() {
		return monday;
	}

	public void setMonday(boolean monday) {
		this.monday = monday;
	}

	public boolean isTuesday() {
		return tuesday;
	}

	public void setTuesday(boolean tuesday) {
		this.tuesday = tuesday;
	}

	public boolean isWednesday() {
		return wednesday;
	}

	public void setWednesday(boolean wednesday) {
		this.wednesday = wednesday;
	}

	public boolean isThursday() {
		return thursday;
	}

	public void setThursday(boolean thursday) {
		this.thursday = thursday;
	}

	public boolean isFriday() {
		return friday;
	}

	public void setFriday(boolean friday) {
		this.friday = friday;
	}

	public int getStartHour() {
		return startHour;
	}

	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}

	public int getStartMinute() {
		return startMinute;
	}

	public void setStartMinute(int startMinute) {
		this.startMinute = startMinute;
	}

	public int getEndHour() {
		return endHour;
	}

	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}

	public int getEndMinute() {
		return endMinute;
	}

	public void setEndMinute(int endMinute) {
		this.endMinute = endMinute;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
