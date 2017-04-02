package com.nmvk.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Offering implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	OfferingKey offerKey;
	
	@Column(name = "max")
	Integer max;
	
	@Column(name = "waitlist")
	Integer waitList;
	
	@Column(name = "remaining")
	Integer remaining;

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
}
