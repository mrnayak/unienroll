package com.nmvk.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.hibernate.annotations.Type;

@Entity
public class Semester implements Serializable {

	/*
	 * Added to ignore serialization warning.
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	SemKey key;

	@Column(name = "STATUS")
	@Type(type = "com.nmvk.type.BooleanType")
	private boolean status;

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public SemKey getKey() {
		return key;
	}

	public void setKey(SemKey key) {
		this.key = key;
	}

}