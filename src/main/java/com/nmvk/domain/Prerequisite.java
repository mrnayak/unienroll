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
public class Prerequisite implements Serializable {

	/*
	 * Added to ignore serialization warning.
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	PrerepKey key;

	public PrerepKey getKey() {
		return key;
	}

	public void setKey(PrerepKey key) {
		this.key = key;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}

