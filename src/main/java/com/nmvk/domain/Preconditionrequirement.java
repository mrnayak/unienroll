package com.nmvk.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entity class representing Schedule table.
 * 
 * @author Raghav
 *
 */
@Entity
public class Preconditionrequirement implements Serializable {

	/*
	 * Added to ignore serialization warning.
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	PreConKey key;

	@Column(name = "GPA")
	float gpa;
	
	public float getGpa() {
		return gpa;
	}

	public void setGpa(float gpa) {
		this.gpa = gpa;
	}

	public PreConKey getKey() {
		return key;
	}

	public void setKey(PreConKey key) {
		this.key = key;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

		
}


